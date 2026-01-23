package com.fashioncart.command;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fashioncart.dto.ProductDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Product;

public class ViewCartCommand implements Command {

    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse res) {

        HttpSession session = req.getSession(false);
        if (session == null) return false;

        List<Product> rawCart =
            (List<Product>) session.getAttribute("cartList");

        if (rawCart == null || rawCart.isEmpty()) {
            return false;
        }

        Map<String, ProductDTO> map = new LinkedHashMap<>();//key=productId value=productDto used to calculate the quantity

        for (Product p : rawCart) {
            if (!map.containsKey(p.getId())) {
                map.put(p.getId(),
                    new ProductDTO(
                        p.getId(),
                        p.getName(),
                        p.getCategory(),
                        p.getPrice()
                    )
                );
            } else {
                map.get(p.getId()).incrementQuantity();
            }
        }

        List<ProductDTO> cartDTOList =
            new ArrayList<>(map.values());

        double totalAmount = 0;
        for (ProductDTO dto : cartDTOList) {
            totalAmount += dto.getPrice() * dto.getQuantity();
        }

        req.setAttribute("cartList", cartDTOList);
        session.setAttribute("totalAmount", totalAmount);

        return true; //cart.jsp
    }
}
