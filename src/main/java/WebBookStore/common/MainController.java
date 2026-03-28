package WebBookStore.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class MainController {

	@RequestMapping("")
	public String index(Model model) {
		// 💡 무한 루프를 유발하던 코드를 지우고, 도서 목록 주소로 바로 토스(Redirect) 합니다!
		return "redirect:/book/list";
	}

	@RequestMapping(value = "msg", produces = "text/plain;charset=utf-8")
	@ResponseBody // 페이지를 리턴하는 것이 아니라 문자열을 리턴하여 화면에 표시
	public String koreaChar(String msg) {
		System.out.println(msg);
		return msg;
	}

}