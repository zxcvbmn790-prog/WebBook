package admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.AdminBookService;

@WebServlet("/admin/*")
public class AdminBookController extends HttpServlet {
    private AdminBookService service;

    public AdminBookController() {
        service = new AdminBookService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        String uri = req.getRequestURI();
        String[] command = uri.split("/"); 
        String page = "";

        switch (command[2]) {
            case "insert":
                
                page = "insert";
                break;
            case "update":
                
                page = "update";
                break;
        }
        
        if(!page.equals("")) {
    		req.getRequestDispatcher("/WEB-INF/views/"+page+".jsp").forward(req, resp);
    	}
    }
}
