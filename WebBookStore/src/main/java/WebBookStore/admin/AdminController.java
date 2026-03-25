package WebBookStore.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	// 수정폼 열기
	@RequestMapping("/updateform")
	public String updateform(Model model) {
		String contentPage = "/WEB-INF/views/admin/updateform.jsp";
		model.addAttribute("contentPage", contentPage);
		return "layout/layout";
	}
	
	// 수정 처리
	@RequestMapping("/update")
	public String update(AdminVO admin, Model model) {
		int result = adminService.updateBook(admin);

		
		return "layout/layout";
	}
}
