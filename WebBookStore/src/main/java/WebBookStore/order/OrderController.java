package WebBookStore.order;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import WebBookStore.book.BookVO;
import WebBookStore.book.UserBookService;
import WebBookStore.cart.CartService;
import WebBookStore.cart.CartVO;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserBookService bookService;

	// 1. 주문서 작성 페이지
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkout(@RequestParam(value = "isbn", required = false) Integer isbn,
			@RequestParam(value = "amount", defaultValue = "1") int amount,
			@RequestParam(value = "buyNow", defaultValue = "false") boolean buyNow, HttpSession session, Model model) {

		String userid = (String) session.getAttribute("loginUser");
		if (userid == null)
			return "redirect:/member/login";

		List<CartVO> orderList = new ArrayList<>();
		int sumMoney = 0;

		if (buyNow && isbn != null) {
			// [바로구매] 전달받은 ISBN으로 상품 정보 구성
			BookVO book = bookService.getBook(isbn);
			CartVO item = new CartVO();
			item.setIsbn(isbn);
			item.setBookname(book.getBookname());
			item.setPrice(book.getPrice());
			item.setAmount(amount);
			item.setImage(book.getImage());
			item.setTotalPrice(Integer.parseInt(book.getPrice()) * amount);

			orderList.add(item);
			sumMoney = item.getTotalPrice();
			model.addAttribute("isBuyNow", true);
		} else {
			// [장바구니 구매] 기존 장바구니 전체 목록 가져오기
			orderList = cartService.getCartList(userid);
			if (orderList == null || orderList.isEmpty())
				return "redirect:/cart/list";

			for (CartVO cart : orderList) {
				sumMoney += cart.getTotalPrice();
			}
			model.addAttribute("isBuyNow", false);
		}

		model.addAttribute("cartList", orderList);
		model.addAttribute("sumMoney", sumMoney);
		model.addAttribute("contentPage", "/WEB-INF/views/order/checkout.jsp");
		return "layout/layout";
	}

	// 2. 실제 결제 및 주문 처리
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public String pay(String receiver, String phone, String address,
			@RequestParam(value = "isBuyNow", defaultValue = "false") boolean isBuyNow,
			@RequestParam(value = "isbn", required = false) Integer isbn,
			@RequestParam(value = "amount", defaultValue = "0") int amount, HttpSession session) {

		String userid = (String) session.getAttribute("loginUser");
		if (userid == null)
			return "redirect:/member/login";

		// 바로구매 상품 리스트 혹은 장바구니 리스트 결정
		List<CartVO> orderItems = new ArrayList<>();

		if (isBuyNow && isbn != null) {
			// [핵심] 바로구매 시 장바구니 DB를 거치지 않고 이 상품 정보만 리스트에 담음
			BookVO book = bookService.getBook(isbn);
			CartVO item = new CartVO();
			item.setIsbn(isbn);
			item.setBookname(book.getBookname());
			item.setPrice(book.getPrice());
			item.setAmount(amount);
			item.setTotalPrice(Integer.parseInt(book.getPrice()) * amount);
			orderItems.add(item);
		} else {
			// 장바구니 전체 결제
			orderItems = cartService.getCartList(userid);
		}

		if (orderItems == null || orderItems.isEmpty())
			return "redirect:/cart/list";

		// 주문 처리 실행
		int result = orderService.placeOrder(userid, orderItems, receiver, phone, address);

		if (result > 0) {
			return "redirect:/order/complete";
		}
		return "redirect:/order/checkout?error=true";
	}

	@RequestMapping(value = "/complete", method = RequestMethod.GET)
	public String complete(Model model) {
		model.addAttribute("contentPage", "/WEB-INF/views/order/complete.jsp");
		return "layout/layout";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpSession session, Model model) {
		String userid = (String) session.getAttribute("loginUser");
		if (userid == null)
			return "redirect:/member/login";

		List<OrderVO> orderList = orderService.getOrderList(userid);
		model.addAttribute("orderList", orderList);
		model.addAttribute("contentPage", "/WEB-INF/views/order/list.jsp");
		return "layout/layout";
	}
}