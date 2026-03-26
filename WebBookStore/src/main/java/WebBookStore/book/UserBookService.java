package WebBookStore.book;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBookService {

	@Autowired
	private UserBookDAO dao;

	public List<BookVO> getBookList(String searchType, String keyword) {
		return dao.findAll(searchType, keyword);
	}

	public BookVO getBook(int isbn) {
		return dao.findByIsbn(isbn);
	}
}