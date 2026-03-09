package user;

import java.util.List;
import Book.BookVO;

public class UserBookService {

    private UserBookDAO dao = new UserBookDAO();

    public List<BookVO> getAllBooks() {
        return dao.findAll();
    }

    public BookVO getBookById(int isbn) {
        return dao.findById(isbn);
    }
}