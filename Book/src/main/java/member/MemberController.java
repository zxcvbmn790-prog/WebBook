package member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.MemberService;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
    private MemberService service;

    public MemberController() {
        service = new MemberService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        String uri = req.getRequestURI();
        String[] command = uri.split("/"); 
        String page = "";

        switch (command[2]) {
            case "login":
                
            	page = "login";
                break;
            case "register":

            	page = "register";
                break;
            case "logout":
                
            	page = "logout";
                break;
        }
        
        if(!page.equals("")) {
    		req.getRequestDispatcher("/WEB-INF/views/"+page+".jsp").forward(req, resp);
    	}
    }
}
