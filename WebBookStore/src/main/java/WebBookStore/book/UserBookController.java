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
			@RequestParam(value = "viewAll", defaultValue = "false") boolean viewAll,
			@RequestParam(value = "keyword", required = false) String keyword, Model model) {

		// 검색어가 없고, '전체' 카테고리의 1페이지일 때만 메인 화면 노출
		if ("전체".equals(category) && page == 1 && !viewAll && (keyword == null || keyword.isEmpty())) {
			model.addAttribute("itBooks", bookService.getTopBooksByCategory("인공지능"));
			model.addAttribute("novelBooks", bookService.getTopBooksByCategory("초보자를 위한 컴퓨터 책"));
			model.addAttribute("economyBooks", bookService.getTopBooksByCategory("경영전략/혁신"));
			model.addAttribute("isMain", true);
		} else {
			int limit = 4;
			int offset = (page - 1) * limit;

			List<BookVO> list;
			int totalCount;

			// 검색어가 있으면 전체 검색, 없으면 카테고리 페이징 처리
			if (keyword != null && !keyword.isEmpty()) {
				list = bookService.getBookList("title", keyword);
				totalCount = list.size();
			} else {
				list = bookService.getBookListByPage(category, offset, limit, viewAll);
				totalCount = bookService.getTotalCount(category);
			}

			int totalPages = (int) Math.ceil((double) totalCount / limit);
			int blockLimit = 5;
			int startPage = (((int) Math.ceil((double) page / blockLimit)) - 1) * blockLimit + 1;
			int endPage = Math.min(startPage + blockLimit - 1, totalPages);

			model.addAttribute("list", list);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("isMain", false);
			model.addAttribute("keyword", keyword);
		}

		model.addAttribute("category", category);
		model.addAttribute("currentPage", page);
		model.addAttribute("viewAll", viewAll);
		model.addAttribute("contentPage", "/WEB-INF/views/book/views.jsp");

		return "layout/layout";
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET) // 메서드 수준 매핑
	public String view(@RequestParam("isbn") int isbn, Model model) {
		model.addAttribute("book", bookService.getBook(isbn));
		model.addAttribute("contentPage", "/WEB-INF/views/book/view.jsp");
		return "layout/layout";
	}

}