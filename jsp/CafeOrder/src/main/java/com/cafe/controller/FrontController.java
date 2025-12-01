package com.cafe.controller;

import java.io.IOException;

import com.cafe.action.Action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CafeServlet")
public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String command = request.getParameter("command");
        if (command == null) {
            command = "product_list";
        }
        
        ActionFactory af = ActionFactory.getInstance();
        Action action = af.getAction(command);
        
        if (action != null) {
            action.execute(request, response);
        }
    }
}
