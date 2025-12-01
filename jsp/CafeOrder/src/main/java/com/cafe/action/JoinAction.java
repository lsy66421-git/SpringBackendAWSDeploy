package com.cafe.action;

import java.io.IOException;

import com.cafe.dao.MemberDAO;
import com.cafe.dto.MemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JoinAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");
        String pwd = request.getParameter("pwd");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        MemberDTO mDto = new MemberDTO();
        mDto.setUserid(userid);
        mDto.setPwd(pwd);
        mDto.setName(name);
        mDto.setEmail(email);
        mDto.setPhone(phone);
        mDto.setAdmin(0);
        
        MemberDAO mDao = MemberDAO.getInstance();
        int result = mDao.insertMember(mDto);
        
        if (result == 1) {
            response.sendRedirect("CafeServlet?command=login_form");
        } else {
            request.setAttribute("message", "Registration failed.");
            new JoinFormAction().execute(request, response); 
        }
    }
}
