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
	public String list(@RequestParam(value = "category", defaultValue = "전체") String category,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "viewAll", defaultValue = "false") boolean viewAll, Model model) {

		int limit = 4;
		int offset = (page - 1) * limit;

		List<BookVO> list = bookService.getBookListByPage(category, offset, limit, viewAll);
		int totalCount = bookService.getTotalCount(category);

		boolean hasNext = (page * limit) < totalCount;

		// JSP로 넘길 데이터들
		model.addAttribute("list", list);
		model.addAttribute("category", category);
		model.addAttribute("currentPage", page);
		model.addAttribute("hasNext", hasNext);
		model.addAttribute("viewAll", viewAll);

		model.addAttribute("contentPage", "/WEB-INF/views/book/views.jsp");
		return "layout/layout";
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(@RequestParam("isbn") int isbn, Model model) {
		// 선택한 책의 정보를 DB에서 가져와서 "book"이라는 이름으로 화면에 넘김
		model.addAttribute("book", bookService.getBook(isbn));

		// 알맹이 화면으로 view.jsp를 지정
		model.addAttribute("contentPage", "/WEB-INF/views/book/view.jsp");

		return "layout/layout";
	}
}