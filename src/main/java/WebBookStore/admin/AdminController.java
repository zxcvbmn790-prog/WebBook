package WebBookStore.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/insertform")
	public String insertform(Model model) {
		// 수정 폼과 똑같이 생겼으므로 updateform.jsp를 재활용합니다!
		model.addAttribute("contentPage", "/WEB-INF/views/admin/updateform.jsp");
		return "layout/layout";
	}

	@RequestMapping("/insert")
	public String insert(AdminVO admin) {
		adminService.insertBook(admin);
		return "redirect:/book/list"; // 등록 후 전체 도서 목록으로 이동
	}

	@RequestMapping("/updateform")
	public String updateform(@RequestParam("isbn") int isbn, Model model) {
		model.addAttribute("admin", adminService.getBookById(isbn));
		model.addAttribute("contentPage", "/WEB-INF/views/admin/updateform.jsp");
		return "layout/layout";
	}

	@RequestMapping("/update")
	public String update(AdminVO admin, RedirectAttributes ra) {
		if (adminService.updateBook(admin)) {
			ra.addFlashAttribute("message", "success");
		} else {
			ra.addFlashAttribute("message", "fail");
		}
		return "redirect:/book/view?isbn=" + admin.getIsbn();
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("isbn") int isbn) { // id 대신 isbn으로 명시
		adminService.deleteBook(isbn);
		return "redirect:/book/list";
	}
}