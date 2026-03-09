package Member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {

	private MemberService service = new MemberService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String[] command = req.getRequestURI().split("/");
		System.out.println(command[2]);
		switch (command[2]) {
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
		req.setCharacterEncoding("UTF-8");
		String uri = req.getRequestURI();

		// 로그인 처리 로직
		if (uri.contains("login_proc")) {

			String id = req.getParameter("username");
			String pw = req.getParameter("password");

			MemberVO loginUser = service.getLoginUser(id, pw);

			if (loginUser != null) {
				HttpSession session = req.getSession();
				session.setAttribute("nickname", loginUser.getNickname());

				if ("admin".equals(loginUser.getId())) {
					session.setAttribute("role", "admin");
					resp.sendRedirect(req.getContextPath() + "/admin/list");
				} else {
					session.setAttribute("role", "user");
					resp.sendRedirect(req.getContextPath() + "/user/list");
				}
				
			} else {
				resp.setContentType("text/html; charset=UTF-8");
				resp.getWriter().println("<script>alert('로그인 실패! 아이디와 비밀번호를 확인해주세요.'); history.back();</script>");
			}
		} else if (uri.contains("register_proc")) {
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			String hp = req.getParameter("hp");
			String email = req.getParameter("email");
			String nickname = req.getParameter("nickname");

			MemberVO mv = new MemberVO(id, pw, hp, email, nickname);

			boolean isJoined = service.registerMember(mv);

			resp.setContentType("text/html; charset=UTF-8");
			if (isJoined) {
				resp.getWriter().println("<script>alert('회원가입이 완료되었습니다!'); location.href='" + req.getContextPath()
						+ "/member/login';</script>");
			} else {
				resp.getWriter().println("<script>alert('회원가입에 실패했습니다. 다시 시도해주세요.'); history.back();</script>");
			}
		} else if (uri.contains("logout")) {
			HttpSession session = req.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			resp.sendRedirect(req.getContextPath() + "/user/list");
		}
	}
}