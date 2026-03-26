package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import member.model.User;

@Controller
@RequestMapping("/")
public class MemberController {
	@Autowired
	private member.service.MemberService memberService;
	
	@RequestMapping(value="member", method=RequestMethod.GET)
	public String member(Model model){

		// 해당 페이지 작성
		model.addAttribute("contentPage","/WEB-INF/views/member/member.jsp");

		return "layout/layout";
	}
	/*
	@RequestMapping(value="member", method=RequestMethod.POST)
	public String member(User user, Model model, HttpServletRequest response) {
		System.out.println(user);
		boolean result = memberService.register(user);
		// result값을 확인하여 성공, 실패 시 알람 및 페이지 이동을 처리를 해야 한다.
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 아래의 코드는 mvc모델 규칙을 깨는 코드이다
		// 모델에 성공여부룰 확인하는 메시지를 입력하여 처리해야함
		if (result) {
			 out.println("<script>");
			 out.println("alert('회원가입 성공!');");
			 out.println("location.href='/'");
			 out.println("</script>");
			} else {
			 out.println("<script>");
			 out.println("alert('회원가입 실패!');");
			 out.println("history.back();");
			 out.println("</script>");
			}
			out.flush();
			out.close();
		
	}*/
	@RequestMapping(value = "member",method = RequestMethod.POST)
	public void member(User user,HttpServletResponse response) throws IOException{
	System.out.println(user);
	boolean result=memberService.register(user);
	//result값을 확인하여 성공, 실패시 알람 및 페이지 이동처리
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();

	if (result) {
	 out.println("<script>");
	 out.println("alert('회원가입 성공!');");
	 out.println("location.href='/'");
	 out.println("</script>");
	} else {
	 out.println("<script>");
	 out.println("alert('회원가입 실패!');");
	 out.println("history.back();");
	 out.println("</script>");
	}
	out.flush();
	out.close();
	}
}
