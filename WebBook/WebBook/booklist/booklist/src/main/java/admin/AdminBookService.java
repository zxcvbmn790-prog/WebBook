package admin;

import java.util.List;

import Book.BookVO;

public class AdminBookService {

    private AdminBookDAO dao;

    public AdminBookService() {
        dao = new AdminBookDAO();
    }

    // 전체 목록
    public List<BookVO> getBookList() {
        return dao.findAll();
    }

    // 등록
    public int insertBook(BookVO book) {
        return dao.insert(book);
    }

    // 수정
    public int updateBook(BookVO book) {
        return dao.update(book);
    }

    // 삭제
    public int deleteBook(int isbn) {
        return dao.delete(isbn);
    }

    // 단건 조회
    public BookVO getBookById(int isbn) {
        return dao.findById(isbn);
    }
}