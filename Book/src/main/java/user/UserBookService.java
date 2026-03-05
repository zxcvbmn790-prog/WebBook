package user;

import java.util.List;

import common.BookVO;
import user.UserBookDAO;

public class UserBookService {
    private UserBookDAO dao;

    public UserBookService() {
        dao = new UserBookDAO();
    }
    
    // 전체출력
    public List<BookVO> getBookList() {
		return dao.findAll();
	}
    
    // 선택출력
    // return dao.findById();
}
