package WebBookStore.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	// /admin → 목록 리다이렉트
	@RequestMapping("")
	public String index() {
		return "redirect:/admin/list";
	}

	// ★ 전체 목록 (추가)
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", adminService.getBookList());
		String contentPage = "/WEB-INF/views/admin/admin_views.jsp";
		model.addAttribute("contentPage", contentPage);
		return "layout/layout";
	}

	// ★ 상세보기 (추가)
	@RequestMapping("/view")
	public String view(@RequestParam("isbn") int isbn, Model model) {
		model.addAttribute("admin", adminService.getBookById(isbn));
		// admin용 상세 뷰가 없으면 updateform을 읽기전용으로 재활용하거나
		// 별도 view.jsp를 만드세요. 여기서는 updateform으로 연결합니다.
		String contentPage = "/WEB-INF/views/admin/updateform.jsp";
		model.addAttribute("contentPage", contentPage);
		return "layout/layout";
	}

	// 등록폼 열기
	@RequestMapping("/insertform")
	public String insertform(Model model) {
		String contentPage = "/WEB-INF/views/admin/insertform.jsp";
		model.addAttribute("contentPage", contentPage);
		return "layout/layout";
	}

	// 등록 처리 → 완료 후 목록으로 redirect
	@RequestMapping("/insert")
	public String insert(AdminVO admin, RedirectAttributes ra) {
		System.out.println(admin);
		ra.addFlashAttribute("kind", "insert");

		if (adminService.insertBook(admin) > 0) {
			ra.addFlashAttribute("message", "success");
		} else {
			ra.addFlashAttribute("message", "fail");
		}
		return "redirect:/admin/list";
	}

	// 수정폼 열기
	@RequestMapping("/updateform")
	public String updateform(@RequestParam("isbn") int isbn, Model model) {
		model.addAttribute("admin", adminService.getBookById(isbn));
		String contentPage = "/WEB-INF/views/admin/updateform.jsp";
		model.addAttribute("contentPage", contentPage);
		return "layout/layout";
	}

	// 수정 처리 → 완료 후 목록으로 redirect
	@RequestMapping("/update")
	public String update(AdminVO admin, RedirectAttributes ra) {
		System.out.println(admin);
		ra.addFlashAttribute("kind", "update");

		if (adminService.updateBook(admin)) {
			ra.addFlashAttribute("message", "success");
		} else {
			ra.addFlashAttribute("message", "fail");
		}
		return "redirect:/admin/list";
	}

	// 삭제 처리 → 완료 후 목록으로 redirect
	@RequestMapping("/delete")
	public String delete(@RequestParam("isbn") int isbn, RedirectAttributes ra) {
		ra.addFlashAttribute("kind", "delete");

		if (adminService.deleteBook(isbn) > 0) {
			ra.addFlashAttribute("message", "success");
		} else {
			ra.addFlashAttribute("message", "fail");
		}
		return "redirect:/admin/list";
	}
}