package com.fashioncart.command;

import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fashioncart.dao.CartDAO;
import com.fashioncart.dao.DeliveryDAO;
import com.fashioncart.dao.OrderItemDAO;
import com.fashioncart.dao.OrdersDAO;
import com.fashioncart.dto.CartItem;
import com.fashioncart.dto.Delivery;
import com.fashioncart.dto.Orders;
import com.fashioncart.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SaveDeliveryCommand implements Command {
	private static final Logger logger=LogManager.getLogger(SaveDeliveryCommand.class);
    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse res) {
    	try {
    		 
        String name = req.getParameter("fullname");
        logger.debug("name : "+name);
        
        String address1 = req.getParameter("address1");
        logger.debug("address 1 : "+ address1);
        
        String address2 = req.getParameter("address2");
        logger.debug("address 2 : "+address2);
        
        String city = req.getParameter("city");
        logger.debug("city : "+city);
        
        String pincode = req.getParameter("pincode");
        logger.debug("pincode : "+pincode);
        
        String mobile = req.getParameter("mobile");
        logger.debug("mobile : "+mobile);
        
        
        
        // checking if any value is null 
        if (name == null || city == null || mobile == null) {
        	return false;
        }

        HttpSession session = req.getSession(false);
        if (session == null) {
        	return false;
        }
        // getting the logged in user from session 
        User user = (User) session.getAttribute("loggedUser");
        
        //getting the paymenyMode setted in  processPaymenCommand
        String paymentMode = (String) session.getAttribute("paymentMode");

        if (user == null || paymentMode == null) {
        	return false;
        }

        // it is used to get the cart items from db 
        CartDAO cartDAO = new CartDAO();
        List<CartItem> cartItems = cartDAO.getCartItems(user.getUserId());

        if (cartItems == null || cartItems.isEmpty()) {
        	return false;
        }

        //calculating the total amount using cartList 
        double totalAmount = 0;
        for (CartItem item : cartItems) {
            totalAmount += item.getProduct().getPrice() * item.getQuantity();
        }
        
        
        // saving the order details in orders table 
        Orders order = new Orders(
        					totalAmount,
        					new Timestamp(System.currentTimeMillis()),
        					paymentMode,
        					"ORDERED"
        					);

        // getting the order id (saveOrder will return the order id)
        OrdersDAO ordersDAO = new OrdersDAO();
        int orderId = ordersDAO.saveOrders(order);
        
        //saving the orders in order_item table 
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        for (CartItem item : cartItems) {
            orderItemDAO.saveOrderItem(
                orderId,
                item.getProduct(),
                item.getQuantity()
            );
        }
        
        // saving the delivery details to the delivery table 
        Delivery delivery = new Delivery(
            orderId, name, address1, address2, city, pincode, mobile
        );
        new DeliveryDAO().saveDeliveryDetails(delivery);

        //clearing the cartitems from db
        cartDAO.clearCart(user.getUserId());
        
        // it gets the cart count after updation 
		int cartCount = cartDAO.getCartCount(user.getUserId());
		session.setAttribute("cartCount", cartCount);
        session.removeAttribute("paymentMode");


        req.setAttribute("orderId", orderId);
        req.setAttribute("city", city);

        return true; // thankyou.jsp
    }catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage());
		return false;
	}
    }
}
