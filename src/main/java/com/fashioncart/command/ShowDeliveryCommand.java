package com.fashioncart.command;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fashioncart.dao.OrderItemDAO;
import com.fashioncart.dao.OrdersDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Orders;
import util.Product;

public class ShowDeliveryCommand implements Command {

    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse res) {

        // 1️⃣ Get delivery fields
        String name = req.getParameter("fullname");
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");
        String city = req.getParameter("city");
        String pincode = req.getParameter("pincode");
        String mobile = req.getParameter("mobile");

        if (name == null || city == null || mobile == null) {
            return false;
        }

        // 2️⃣ Get session
        HttpSession session = req.getSession(false);
        if (session == null) return false;

        List<Product> cartList =
            (List<Product>) session.getAttribute("cartList");

        String paymentMode =
            (String) session.getAttribute("paymentMode");

        if (cartList == null || paymentMode == null) {
            return false;
        }

        // 3️⃣ Calculate quantity + total
        Map<String, Integer> qtyMap = new HashMap<>();
        Map<String, Product> productMap = new HashMap<>();
        double totalAmount = 0;

        for (Product p : cartList) {
            qtyMap.put(p.getId(), qtyMap.getOrDefault(p.getId(), 0) + 1);
            productMap.put(p.getId(), p);
            totalAmount += p.getPrice();
        }

        // 4️⃣ Create order
        Orders order = new Orders(
            totalAmount,
            new Timestamp(System.currentTimeMillis()),
            paymentMode,
            "ORDERED"
        );

        OrdersDAO ordersDAO = new OrdersDAO();
        int orderId = ordersDAO.saveOrders(order);
        if (orderId <= 0) return false;

        // 5️⃣ Save order items
        OrderItemDAO itemDAO = new OrderItemDAO();
        for (String productId : qtyMap.keySet()) {
            itemDAO.saveOrderItem(
                orderId,
                productMap.get(productId),
                qtyMap.get(productId)
            );
        }

        // 6️⃣ (Optional) Save delivery address
        // deliveryDAO.save(orderId, name, address1, address2, city, pincode, mobile);

        // 7️⃣ Clear session
        session.removeAttribute("cartList");
        session.removeAttribute("paymentMode");

        // 8️⃣ Send data to thankyou.jsp
        req.setAttribute("orderId", orderId);
        req.setAttribute("city", city);
        req.setAttribute("totalAmount", totalAmount);

        return true; // → thankyou.jsp
    }
}
