package admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Book.BookVO;

@WebServlet("/admin/*")
public class AdminBookController extends HttpServlet {

    private AdminBookService service;

    public AdminBookController() {
        service = new AdminBookService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        // 세션 체크 - admin 아니면 로그인 페이지로
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        if (!"admin".equals(role)) {
            resp.sendRedirect(req.getContextPath() + "/member/login");
            return;
        }

        String path = req.getPathInfo();
        String command = path.substring(1);
        String page = "";

        switch (command) {

            // 관리자 목록
            case "list":
                List<BookVO> list = service.getBookList();
                req.setAttribute("list", list);
                page = "admin_views";
                break;

            // 등록 폼
            case "insertform":
                page = "insertform";
                break;

            // 등록 처리
            case "insert":
                BookVO newBook = new BookVO(
                        Integer.parseInt(req.getParameter("isbn")),
                        req.getParameter("bookname"),
                        req.getParameter("author"),
                        req.getParameter("publisher"),
                        req.getParameter("image"),
                        req.getParameter("price")
                );
                service.insertBook(newBook);
                resp.sendRedirect(req.getContextPath() + "/admin/list");
                return;

            // 수정 폼
            case "updateform":
                int isbnU = Integer.parseInt(req.getParameter("isbn"));
                BookVO book = service.getBookById(isbnU);
                req.setAttribute("book", book);
                page = "updateform";
                break;

            // 수정 처리
            case "update":
                BookVO updBook = new BookVO(
                        Integer.parseInt(req.getParameter("isbn")),
                        req.getParameter("bookname"),
                        req.getParameter("author"),
                        req.getParameter("publisher"),
                        req.getParameter("image"),
                        req.getParameter("price")
                );
                service.updateBook(updBook);
                resp.sendRedirect(req.getContextPath() + "/admin/list");
                return;

            // 삭제 처리
            case "delete":
                int isbnD = Integer.parseInt(req.getParameter("isbn"));
                service.deleteBook(isbnD);
                resp.sendRedirect(req.getContextPath() + "/admin/list");
                return;
        }

        if (!page.equals("")) {
            req.getRequestDispatcher("/WEB-INF/views/admin/" + page + ".jsp").forward(req, resp);
        }
    }
}