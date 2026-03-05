package member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/")
public class memberController extends HttpServlet {

	private memberService service = new memberService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String[] command = req.getRequestURI().split("/");
		System.out.println(command[1]);
		switch (command[1]) {
		case "login":
			req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
			break;
		case "register":
			req.getRequestDispatcher("/WEB-INF/views/member/register.jsp").forward(req, resp);
			break;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		System.out.println(uri);
		// 로그인 처리 로직
		if (uri.contains("login_proc")) {
			String id = req.getParameter("username");
			String pw = req.getParameter("password");

			// Service -> DAO -> H2 데이터 대조
			boolean isSuccess = service.isLogin(id, pw);

			if (isSuccess) {
				HttpSession session = req.getSession();
				session.setAttribute("username", id);
				resp.sendRedirect(req.getContextPath() + "/login"); // 성공 시 로그인 페이지(세션있음)로 리다이렉트
			} else {
				resp.setContentType("text/html; charset=UTF-8");
				resp.getWriter().println("<script>alert('로그인 실패!'); history.back();</script>");
			}
		}
	}
}
