package com.cafe.action;

import java.io.IOException;
import java.util.List;

import com.cafe.dao.ProductDAO;
import com.cafe.dto.ProductDTO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductListAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        String url = "views/product/list.jsp";
        
        ProductDAO pDao = ProductDAO.getInstance();
        List<ProductDTO> productList = pDao.selectAllProducts();
        
        request.setAttribute("productList", productList);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
