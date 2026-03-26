package WebBookStore.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/book")
public class UserBookController {

	@Autowired
	private UserBookService bookService;

	// 전체 출력
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@RequestParam(value = "searchType", required = false) String searchType,
			@RequestParam(value = "keyword", required = false) String keyword, Model model) {

		List<BookVO> list = bookService.getBookList(searchType, keyword);
		model.addAttribute("list", list);

		String contentPage = "/WEB-INF/views/book/views.jsp";
		model.addAttribute("contentPage", contentPage);

		return "layout/layout";
	}

	// 선택 출력
	@RequestMapping("/view")
	public String view(@RequestParam("isbn") int isbn, Model model) {
		model.addAttribute("book", bookService.getBook(isbn));
		model.addAttribute("contentPage", "/WEB-INF/views/book/view.jsp");
		return "layout/layout";
	}
}