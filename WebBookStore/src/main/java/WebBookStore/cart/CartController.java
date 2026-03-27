package WebBookStore.cart;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import WebBookStore.admin.AdminService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;


	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute CartVO cartVO) {

		if (cartVO.getUserid() == null || cartVO.getUserid().isEmpty()) {
			return "redirect:/member/login";
		}

		cartService.insertOrUpdateCart(cartVO);

		return "redirect:/cart/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpSession session, Model model) {
		String userid = (String) session.getAttribute("loginUser");

		if (userid == null) {
			return "redirect:/member/login";
		}

		List<CartVO> cartList = cartService.getCartList(userid);
		model.addAttribute("cartList", cartList);

		int sumMoney = 0;
		for (CartVO cart : cartList) {
			sumMoney += cart.getTotalPrice();
		}
		model.addAttribute("sumMoney", sumMoney);

		model.addAttribute("contentPage", "/WEB-INF/views/cart/list.jsp");
		return "layout/layout";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("isbn") int isbn) { // id 대신 isbn으로 명시
		cartService.deleteBook(isbn);
		return "redirect:/cart/list";
	}
	
}