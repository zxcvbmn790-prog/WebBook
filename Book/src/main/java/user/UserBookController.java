package user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.BookVO;

@WebServlet("/user/*")
public class UserBookController extends HttpServlet {

    private UserBookService service;

    public UserBookController() {
        service = new UserBookService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String path = req.getPathInfo();       // "/views" 또는 "/view"
        String command = path.substring(1);    // "views" 또는 "view"
        String page = "";

        switch (command) {

            // 전체 출력
            case "views":
                List<BookVO> list = service.getBookList();
                req.setAttribute("list", list);
                page = "views";
                break;

            // 상세 보기
            case "view":
                String isbnParam = req.getParameter("isbn");

                // isbn 없으면 목록으로 리다이렉트
                if (isbnParam == null || isbnParam.isEmpty()) {
                    resp.sendRedirect(req.getContextPath() + "/user/views");
                    return;
                }

                int isbn = Integer.parseInt(isbnParam);
                BookVO book = service.getBookById(isbn);
                req.setAttribute("book", book);
                page = "view";
                break;
        }

        if (!page.equals("")) {
            req.getRequestDispatcher("/WEB-INF/views/" + page + ".jsp").forward(req, resp);
        }
    }
}