package user;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Book.BookVO;

import java.io.IOException;
import java.util.List;

@WebServlet("/user/*")
public class UserBookController extends HttpServlet {

    private UserBookService service = new UserBookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getPathInfo(); // /list  또는  /view

        if ("/list".equals(path)) {
            // 전체 목록
            List<BookVO> list = service.getAllBooks();
            req.setAttribute("list", list);
            req.getRequestDispatcher("/WEB-INF/views/user/views.jsp").forward(req, resp);

        } else if ("/view".equals(path)) {
            String isbnParam = req.getParameter("isbn");
            
            // isbn 없으면 목록으로 리다이렉트
            if (isbnParam == null || isbnParam.isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/user/list");
                return;
            }
            
            int isbn = Integer.parseInt(isbnParam);
            BookVO book = service.getBookById(isbn);
            req.setAttribute("book", book);
            req.getRequestDispatcher("/WEB-INF/views/user/view.jsp").forward(req, resp);
        }
    }
}