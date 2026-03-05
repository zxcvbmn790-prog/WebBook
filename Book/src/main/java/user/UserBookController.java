package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            case "views":
            	
                page = "views";
                break;
            case "view":
            	
            	page = "view";
                break;
        }
        
        if(!page.equals("")) {
    		req.getRequestDispatcher("/WEB-INF/views/"+page+".jsp").forward(req, resp);
    	}
    }
}
