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
    	return true;
    }
}
