package user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.BookVO;
import user.UserBookService;


@WebServlet("/user/*")
public class UserBookController extends HttpServlet {
    private UserBookService service;

    public UserBookController() {
        service = new UserBookService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        String uri = req.getRequestURI();
        String[] command = uri.split("/"); 
        String page = "";

        switch (command[2]) {
        	// 전체출력
            case "views":
            	// 데이터를 가져오는 작업
        		List<BookVO> list=service.getBookList();
        		// 데이터를 저장하는 작업
        		req.setAttribute("list",list);
        		// 저장된 데이터를 표시하는 페이지 이동
        		// req.getRequestDispatcher("/WEB-INF/views/views.jsp").forward(req, resp);
                page = "views";
                break;
               
            // 선택출력
            case "view":
            	
            	page = "view";
                break;
        }
        
        if(!page.equals("")) {
    		req.getRequestDispatcher("/WEB-INF/views/"+page+".jsp").forward(req, resp);
    	}
    }
}
