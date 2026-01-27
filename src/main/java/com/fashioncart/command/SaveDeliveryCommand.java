package com.fashioncart.command;

import java.sql.Timestamp;
import java.util.List;

import com.fashioncart.dao.CartDAO;
import com.fashioncart.dao.DeliveryDAO;
import com.fashioncart.dao.OrderItemDAO;
import com.fashioncart.dao.OrdersDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.CartItem;
import util.Delivery;
import util.Orders;
import util.User;

public class SaveDeliveryCommand implements Command {

    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse res) {

        String name = req.getParameter("fullname");
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");
        String city = req.getParameter("city");
        String pincode = req.getParameter("pincode");
        String mobile = req.getParameter("mobile");

        if (name == null || city == null || mobile == null) {
        	return false;
        }

        HttpSession session = req.getSession(false);
        if (session == null) {
        	return false;
        }

        User user = (User) session.getAttribute("loggedUser");
        String paymentMode = (String) session.getAttribute("paymentMode");

        if (user == null || paymentMode == null) {
        	return false;
        }

        CartDAO cartDAO = new CartDAO();
        List<CartItem> cartItems = cartDAO.getCartItems(user.getUserId());

        if (cartItems == null || cartItems.isEmpty()) {
        	return false;
        }

        double totalAmount = 0;
        for (CartItem item : cartItems) {
            totalAmount += item.getProduct().getPrice() * item.getQuantity();
        }

        Orders order = new Orders(
            totalAmount,
            new Timestamp(System.currentTimeMillis()),
            paymentMode,
            "ORDERED"
        );

        OrdersDAO ordersDAO = new OrdersDAO();
        int orderId = ordersDAO.saveOrders(order);

        OrderItemDAO orderItemDAO = new OrderItemDAO();
        for (CartItem item : cartItems) {
            orderItemDAO.saveOrderItem(
                orderId,
                item.getProduct(),
                item.getQuantity()
            );
        }

        Delivery delivery = new Delivery(
            orderId, name, address1, address2, city, pincode, mobile
        );
        new DeliveryDAO().saveDeliveryDetails(delivery);

        //CLEAR CART FROM DB
        cartDAO.clearCart(user.getUserId());
        
		int cartCount = cartDAO.getCartCount(user.getUserId());
		session.setAttribute("cartCount", cartCount);
        session.removeAttribute("paymentMode");
        req.setAttribute("orderId", orderId);
        req.setAttribute("city", city);

        return true; // thankyou.jsp
    }
}
