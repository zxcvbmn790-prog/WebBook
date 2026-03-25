

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/book")
public class UserBookController {

    @Autowired
    private UserBookService bookService;

    // 전체 출력
    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", bookService.getBookList());
        model.addAttribute("contentPage", "/WEB-INF/views/book/list.jsp");
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