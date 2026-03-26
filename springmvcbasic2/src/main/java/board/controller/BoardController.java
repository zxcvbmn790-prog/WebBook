package board.controller;

import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import board.model.Board;
import board.model.BoardForm;
import board.service.BoardService;

@Controller
//@RequestMapping("/board/")  // 사용 가능
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;

	// 페이지 요청에 대한 처리
	@RequestMapping(value="list", method = RequestMethod.GET)
	public String list(@RequestParam(value ="requestPage", defaultValue = "1" ) int  reqestPage ,Model model) {
		// 이전에는 전체 리스트를 화면에 나타냈지만 여기서는 페이지 리스트를 나타낸다는 것이 중점이다
		model.addAttribute("pagelist", boardService.getPageList(reqestPage));
		
		model.addAttribute("contentPage", "/WEB-INF/views/board/list.jsp");
		return "layout/layout";
	}
	
	/*
	// mapping에서 name, value가 존재하는데 name은 배열 처리할 때 사용하고 여기서는 value를 사용한다
	@RequestMapping(value="list", method = RequestMethod.GET)
	public String list(Model model) {
		// 이전에는 전체 리스트를 화면에 나타냈지만 여기서는 페이지 리스트를 나타낸다는 것이 중점이다
		model.addAttribute("list", boardService.getList());
		
		model.addAttribute("contentPage", "/WEB-INF/views/board/list.jsp");
		return "layout/layout";
	}
	*/
	
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("contentPage", "/WEB-INF/views/board/write.jsp");
		return "layout/layout";
	}
	
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String write(BoardForm board, RedirectAttributes ra) {
		System.out.println(board);
		if(boardService.insert(board)) {
			ra.addAttribute("msg", "게시글이 등록되었습니다");
			return "redirect:/";
		}else {
			ra.addAttribute("msg", "게시글이 등록이 실패했습니다");
			return "redirect:/";
		}
		//return null;
	}
	
	@RequestMapping(value="view", method = RequestMethod.GET)
	public String view() {
		
		return null;
	}
	
	@RequestMapping(value="update", method = RequestMethod.GET)
	public String update() {
		
		return null;
	}
	
	@RequestMapping(value="update", method = RequestMethod.POST)
	public String update(Board board) {
		
		return null;
	}
	
	@RequestMapping(value="delete", method = RequestMethod.GET)
	public String delete() {
		
		return null;
	}

}
