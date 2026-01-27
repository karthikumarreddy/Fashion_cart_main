package com.fashioncart.command;

import java.util.List;

import com.fashioncart.dao.ProductDAO;
import com.fashioncart.dto.Product;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ListProductCommand implements Command {

    @Override
    public boolean execute(HttpServletRequest request,
                           HttpServletResponse response) {
    		//getting category from home.jsp
        String category = request.getParameter("category");
        
        //getting products and storing in List
        ProductDAO productDAO = new ProductDAO();
        List<Product> products;

        if (category == null || category.equalsIgnoreCase("ALL")) {
            products = productDAO.getAllProductsList();
        } else {
        	/*getting the product based on category from db 
        	 * and stored to an list
        	 */
            products = productDAO.getProductsByCategoryList(category);
        }
        if (products == null || products.isEmpty()) {
            return false; //error.jsp
        }
        
        
        
        /*setting the list of products in session 
         * and it will be accessed in home.jsp to display products
         */
        HttpSession session=request.getSession();
        session.setAttribute("productList", products);

        return true; //Home.jsp
    }
}
