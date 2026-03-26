package springmvcbasic2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import board.repository.BoardDAO;
import board.repository.BoardDAOH2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
public class BoardTest {

    @Autowired
    BoardDAOH2 boardDAO;


    @Test
    public void testConnection() throws Exception {
        System.out.println(boardDAO.getDs());
    }

    @Test
    public void testDAO() {
       // System.out.println(boardDAO.findAll(0, 0));
       // System.out.println(boardDAO.count());
        System.out.println(boardDAO.findAll(1, 10));
    }
    
}