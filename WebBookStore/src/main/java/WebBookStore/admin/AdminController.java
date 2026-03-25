package WebBookStore.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	// /admin
	@RequestMapping("")
	public String index(Model model) {
		model.addAttribute("contentPage", "index");
		return "layout/layout";
	}
	// 수정폼 열기
	@RequestMapping("/updateform")
	public ModelAndView updateform(int isbn, RedirectAttributes ra, HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("admin", adminService.getBookById(isbn));
		
		String contentPage = "/WEB-INF/views/admin/updateform.jsp";
		//model.addAttribute("contentPage", contentPage);
		mv.addObject("contentPage", contentPage);
		
		mv.setViewName("layout/layout");
		return mv;
		//return "layout/layout";
	}
	
	// 수정 처리
	@RequestMapping("/update")
	public String update(AdminVO admin, Model model,
		RedirectAttributes ra) {
			System.out.println(admin);
			ra.addFlashAttribute("kind","update");
			if(adminService.updateBook(admin)) {
				ra.addFlashAttribute("message","success"); 
			}
			else {
				ra.addFlashAttribute("message","fail"); 
			}

			//return "redirect:/phonebook/view?id="+admin.getId();
		/*	
		boolean result = adminService.updateBook(admin);
		if(result) {
			
		}else {
			
		}
		*/
		String contentPage = "/WEB-INF/views/user/views.jsp";
		model.addAttribute("contentPage", contentPage);
		return "layout/layout";
	}
}
