package com.cafe.action;

import java.io.IOException;

import com.cafe.dao.MemberDAO;
import com.cafe.dto.MemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "views/member/login_fail.jsp";
        String userid = request.getParameter("userid");
        String pwd = request.getParameter("pwd");
        
        MemberDAO mDao = MemberDAO.getInstance();
        int result = mDao.userCheck(userid, pwd);
        
        if (result == 1) {
            MemberDTO mDto = mDao.getMember(userid);
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", mDto);
            url = "CafeServlet?command=product_list";
            response.sendRedirect(url); // Redirect to avoid resubmission and change URL
            return;
        } else if (result == 0) {
            request.setAttribute("message", "Password incorrect.");
        } else if (result == -1) {
            request.setAttribute("message", "User ID not found.");
        }
        
        // If fail, forward back to login page (or fail page)
        url = "views/member/login.jsp";
        jakarta.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
