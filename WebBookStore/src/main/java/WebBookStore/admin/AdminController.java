package WebBookStore.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import WebBookStore.cart.CartService;
import WebBookStore.member.MemberVO;
import WebBookStore.order.OrderService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

	// ==========================================
	// 1. 도서 관리
	// ==========================================

	@RequestMapping("/insertform")
	public String insertform(Model model) {
		model.addAttribute("contentPage", "/WEB-INF/views/admin/updateform.jsp");
		return "layout/layout";
	}

	@RequestMapping("/insert")
	public String insert(AdminVO admin) {
		adminService.insertBook(admin);
		return "redirect:/book/list";
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
	public String delete(@RequestParam("isbn") int isbn) {
		adminService.deleteBook(isbn);
		return "redirect:/book/list";
	}

	// ==========================================
	// 2. 회원 관리
	// ==========================================

	// 회원 목록 페이지 (검색 처리 포함) - 중복된 메서드 하나로 통합!
	@RequestMapping("/member/list")
	public String memberList(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
		// 1. 검색어를 포함하여 회원 목록 조회
		model.addAttribute("memberList", adminService.getMemberList(keyword));

		// 2. 검색 후에도 입력창에 검색어가 그대로 남아있게 하기 위해 모델에 담아 전송
		model.addAttribute("keyword", keyword);

		model.addAttribute("contentPage", "/WEB-INF/views/admin/member_list.jsp");
		return "layout/layout";
	}

	@RequestMapping("/member/detail")
	public String memberDetail(@RequestParam("username") String username, Model model) {
		MemberVO member = adminService.getMemberById(username);
		model.addAttribute("member", member);

		model.addAttribute("cartList", cartService.getCartList(username));

		model.addAttribute("orderList", orderService.getOrderList(username));

		model.addAttribute("contentPage", "/WEB-INF/views/admin/member_detail.jsp");
		return "layout/layout";
	}

	@RequestMapping("/member/delete")
	public String memberDelete(@RequestParam("username") String username) {
		adminService.deleteMember(username);
		return "redirect:/admin/member/list";
	}
}