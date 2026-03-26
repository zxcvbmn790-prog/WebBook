package WebBookStore.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model) {
		String contentPage = "/WEB-INF/views/member/login.jsp";
		model.addAttribute("contentPage", contentPage);
		return "layout/layout";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(String username, String password, HttpSession session) {
		MemberVO loginUser = memberService.getLoginUser(username, password);

		if (loginUser != null) {
			session.setAttribute("loginUser", loginUser.getUsername());
			return "redirect:/book/list";
		}

		return "redirect:/member/login?error=true";
	}
	
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(Model model) {
		String contentPage = "/WEB-INF/views/member/register.jsp";
		model.addAttribute("contentPage", contentPage);
		return "layout/layout";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(MemberVO member) {
		boolean isRegistered = memberService.registerMember(member);

		if (isRegistered) {
			return "redirect:/member/login";
		}

		return "redirect:/member/register?error=true";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/member/login";
	}
}