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
			session.setAttribute("loginPW", loginUser.getPassword());
			session.setAttribute("loginEmail", loginUser.getEmail());
			session.setAttribute("loginPhone", loginUser.getPhone());
			session.setAttribute("loginNickname", loginUser.getNickname());
			System.out.println();
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
	
	// MemberController.java에 추가

	@RequestMapping(value = "info", method = RequestMethod.GET)
	public String info(HttpSession session,String username, String password, Model model) {
		MemberVO loginUser = memberService.getLoginUser(username, password);

		if (loginUser != null) {
			session.setAttribute("loginUser", loginUser.getUsername());
			session.setAttribute("loginPW", loginUser.getPassword());
			session.setAttribute("loginEmail", loginUser.getEmail());
			session.setAttribute("loginPhone", loginUser.getPhone());
			session.setAttribute("loginNickname", loginUser.getNickname());
		}
	    // 3. 레이아웃 처리를 위한 경로 설정
	    model.addAttribute("contentPage", "/WEB-INF/views/member/info.jsp");
	    return "layout/layout";
	}

	/*
	 * @RequestMapping(value = "update", method = RequestMethod.POST) public String
	 * update(MemberVO member) { // 정보 수정 처리 boolean isUpdated =
	 * memberService.updateMember(member); System.out.println(isUpdated); if
	 * (isUpdated) { return "redirect:/book/list"; // 수정 성공 시 목록으로 } return
	 * "redirect:/member/info?error=true"; // 실패 시 다시 수정창으로 }
	 */
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(MemberVO member, HttpSession session) {
	    // 1. DB 정보 수정 실행
	    boolean isUpdated = memberService.updateMember(member);
	    
	    if (isUpdated) {
	        // 2. 중요: 세션 정보 갱신 (화면에 즉시 반영하기 위함)
	        // 수정된 member 객체의 내용을 세션에 다시 저장합니다.
	        session.setAttribute("loginUser", member.getUsername());
	        session.setAttribute("loginPW", member.getPassword());
	        session.setAttribute("loginEmail", member.getEmail());
	        session.setAttribute("loginPhone", member.getPhone());
	        session.setAttribute("loginNickname", member.getNickname());
	        
	        System.out.println("수정 성공 및 세션 갱신 완료: " + member.getUsername());
	        return "redirect:/book/list"; 
	    }
	    
	    return "redirect:/member/info?error=true"; 
	}
}