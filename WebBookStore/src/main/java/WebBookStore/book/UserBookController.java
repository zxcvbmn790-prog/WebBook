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

		// 수정된 조건: 카테고리가 '전체'이고, 페이지가 1페이지이며, '전체보기' 모드가 아닐 때만 메인 섹션 노출
		if ("전체".equals(category) && page == 1 && !viewAll) {
			// 실제 데이터에 포함된 카테고리 명칭으로 변경
			model.addAttribute("itBooks", bookService.getTopBooksByCategory("인공지능"));
			model.addAttribute("novelBooks", bookService.getTopBooksByCategory("초보자를 위한 컴퓨터 책"));
			model.addAttribute("economyBooks", bookService.getTopBooksByCategory("경영전략/혁신"));
			model.addAttribute("examBooks", bookService.getTopBooksByCategory("인공지능/빅데이터"));
			model.addAttribute("liberalBooks", bookService.getTopBooksByCategory("컴퓨터공학/전산학 개론"));
			model.addAttribute("isMain", true);
		} else {
			int limit = 4;
			int offset = (page - 1) * limit;

			List<BookVO> list = bookService.getBookListByPage(category, offset, limit, viewAll);
			int totalCount = bookService.getTotalCount(category);
			int totalPages = (int) Math.ceil((double) totalCount / limit);

			// ★ 추가: 페이징 블록 계산 (5개씩 끊기)
			int blockLimit = 5;
			int startPage = (((int) Math.ceil((double) page / blockLimit)) - 1) * blockLimit + 1;
			int endPage = startPage + blockLimit - 1;
			if (endPage > totalPages)
				endPage = totalPages;

			model.addAttribute("list", list);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("startPage", startPage); // 블록 시작 (예: 1, 6, 11...)
			model.addAttribute("endPage", endPage); // 블록 끝 (예: 5, 10, 15...)
			model.addAttribute("hasNext", (page * limit) < totalCount);
			model.addAttribute("isMain", false);
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