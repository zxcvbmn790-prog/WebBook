package auth;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import member.service.MemberService;

@Controller
@RequestMapping("/")
public class AuthController {

	// memberService를 이용할 것이다
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="login", method=RequestMethod.GET)
    public String login(Model model) {
		String contentPage="/WEB-INF/views/login/login.jsp";
		model.addAttribute("contentPage",contentPage);
		return "layout/layout";  // views/layout/layout.jsp를 여는 것이고
	}
    		
   /* @RequestMapping(value="login", method=RequestMethod.POST)
    public String login(String username, String password, HttpSession session, RedirectAttributes ra) {

    	// memberService를 이용하여 해당 사용자가 있는지 여부를 확인
    	// 확인 후 비밀번호가 일치할 경우 true, 그렇지 않을 경우 false 리턴
    	if (memberService.confirmLogin(username,password)) {
    		ra.addAttribute("msg", "로그인 성공");
    		session.setAttribute("loginUsere", username);
    		return "redirect:/";
    	}
    	ra.addAttribute("msg", "로그인 실패");
        /*if ("admin".equals(username) && "1234".equals(password)) {
            session.setAttribute("loginUser", username);
            return "redirect:/";
        }

        return "redirect:/login?error=true";
    }*/
	
	@RequestMapping(value="login", method=RequestMethod.POST)
    public String login(String username, String password
    		, HttpSession session,RedirectAttributes ra) {
    	//memeberService를 이용하여 해당 사용자가 있는지 여부를 확인
    	//확인 후 비밀번호가 일치할 경우 true, 그렇지 않을 경우 false리턴
        if (memberService.confirmLogin(username,password)) {
        	ra.addAttribute("msg","로그인 성공!!");
            session.setAttribute("loginUser", username);
            return "redirect:/";
        }
        ra.addAttribute("msg","로그인 실패!!");
        return "redirect:/login?error=true";
    }
	
    // http://localhost:8888/logout
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}