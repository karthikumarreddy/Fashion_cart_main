package com.fashioncart.command;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fashioncart.dao.DeliveryDAO;
import com.fashioncart.dao.OrderItemDAO;
import com.fashioncart.dao.OrdersDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Delivery;
import util.Orders;
import util.Product;

public class SaveDeliveryCommand implements Command {

	@Override
    public boolean execute(HttpServletRequest req, HttpServletResponse res) {

        // Get delivery fields
        String name = req.getParameter("fullname");
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");
        String city = req.getParameter("city");
        String pincode = req.getParameter("pincode");
        String mobile = req.getParameter("mobile");

        if (name == null || city == null || mobile == null) {
        	System.out.println("name==null");
            return false;
        }

  
        HttpSession session = req.getSession(false);
        if (session == null) {
        	System.out.println("session==null");
        	return false;
        }

        List<Product> cartList =
            (List<Product>) session.getAttribute("cartList");

        String paymentMode =
            (String) session.getAttribute("paymentMode");

        if (cartList == null || paymentMode == null) {
        	System.out.println("Inside cartlist==null");
            return false;
        }

    
        Map<String, Integer> qtyMap = new HashMap<>();
        Map<String, Product> productMap = new HashMap<>();
        double totalAmount = 0;

        for (Product p : cartList) {
            qtyMap.put(p.getId(), qtyMap.getOrDefault(p.getId(), 0) + 1);
            productMap.put(p.getId(), p);
            totalAmount += p.getPrice();
        }

        Orders order = new Orders(
            totalAmount,
            new Timestamp(System.currentTimeMillis()),
            paymentMode,
            "ORDERED"
        );

        OrdersDAO ordersDAO = new OrdersDAO();
        int orderId = ordersDAO.saveOrders(order);
        

     
        OrderItemDAO itemDAO = new OrderItemDAO();
        for (String productId : qtyMap.keySet()) {
            itemDAO.saveOrderItem(
                orderId,
                productMap.get(productId),
                qtyMap.get(productId)
            );
        }

      
        Delivery delivery=new Delivery(orderId, name, address1, address2, city, pincode, mobile);
        DeliveryDAO deliveryDAO=new DeliveryDAO();
         deliveryDAO.saveDeliveryDetails(delivery);

        // Clear session
        session.removeAttribute("cartList");
        session.removeAttribute("paymentMode");

        // Send data to thankyou.jsp
        req.setAttribute("orderId", orderId);
        req.setAttribute("city", city);
        req.setAttribute("totalAmount", totalAmount);

        return true; // → thankyou.jsp
    }

}
