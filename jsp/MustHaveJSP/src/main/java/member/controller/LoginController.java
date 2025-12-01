package member.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.service.LoginService;
import membership.MemberDTO;

@WebServlet("/login.do")
public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	LoginService service = new LoginService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		req.getRequestDispatcher("/12_1/LoginForm.jsp").forward(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String userId = req.getParameter("user_id");
		String userPwd = req.getParameter("user_pw");
		MemberDTO dto = service.getMember(userId,userPwd);
		// 로그인 성공 여부에 따른 처리
		if (dto.getId() != null) { 
		    // 로그인 성공
			req.getSession().setAttribute("UserId", dto.getId());
			req.getSession().setAttribute("UserName", dto.getName());

		    resp.sendRedirect("login.do");
		}
		else {
		    // 로그인 실패
		    req.setAttribute("LoginErrMsg", "로그인 오류입니다.");
		    req.getRequestDispatcher("/12_1/LoginForm.jsp")
		    	.forward(req, resp);
		}
	}
}
