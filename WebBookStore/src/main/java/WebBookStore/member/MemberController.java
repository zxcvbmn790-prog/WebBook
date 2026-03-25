package WebBookStore.member;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/member")
public class MemberController {

	@RequestMapping(value="login", method=RequestMethod.GET)
    public String login(Model model) {
		String contentPage = "/WEB-INF/views/member/login.jsp";
		model.addAttribute("contentPage",contentPage);
		return "layout/layout";
	}
	@RequestMapping(value="register", method=RequestMethod.GET)
    public String register(Model model) {
		String contentPage = "/WEB-INF/views/member/register.jsp";
		model.addAttribute("contentPage",contentPage);
		return "layout/layout";
	}
    		
    @RequestMapping(value="login", method=RequestMethod.POST)
    public String login(String username, String password, HttpSession session) {

        if ("admin".equals(username) && "1234".equals(password)) {
            session.setAttribute("loginUser", username);
            return "redirect:/";
        }
        return "redirect:/login?error=true";
    }
    // http://localhost:8888/logout
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}