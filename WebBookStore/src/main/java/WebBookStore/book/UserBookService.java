

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBookService {

    @Autowired
    private UserBookDAO bookDAO;

    // 전체 출력
    public List<BookVO> getBookList() {
        return bookDAO.findAll();
    }

    // 선택 출력
    public BookVO getBook(int isbn) {
        return bookDAO.findByIsbn(isbn);
    }
}