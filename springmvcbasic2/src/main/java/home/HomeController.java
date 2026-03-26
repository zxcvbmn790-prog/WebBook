package home;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.engine.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // bean생성<bean id="homeController" class="home.HomeController"></bean>
//@Controller 유사한 명령 , @Service @Repository @Component
@RequestMapping("/")
public class HomeController {
	
	// @RequestMapping("/") + @RequestMapping("")
	// http://localhost:8888/
	@RequestMapping("")
	public String index(Model model) {
		String contentPage="/WEB-INF/views/index.jsp";
		model.addAttribute("contentPage",contentPage);
		return "layout/layout";
	}
	
	//http://localhost:8888/msg?msg=안녕하세요
	/*해당코드는 spring에서 사용안함
	 HttpServletResponse response -> 
	 response.setCharacterEncoding("utf-8");
	 response.setContentType("text/plain;charset=utf-8");
	*/
	//produces는 응답할 메시지의 header에 해당 코드를 인코딩 
	@RequestMapping(value="msg", 
			produces = "text/plain;charset=utf-8" )
	@ResponseBody //페이지를 리턴하는 것이 아니라 문자열을 리턴하여 화면에 표시
	public String koreaChar
	(String msg) {
		System.out.println(msg);		
		return msg;		
	}
	
}
