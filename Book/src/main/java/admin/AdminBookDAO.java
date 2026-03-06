package admin;

import common.BookVO;

public class AdminBookDAO {
    // DB 연결

    // 책 정보 입력 (INSERT INTO book VALUES(?, ?, ?, ?, ?, ?))
    public int save(BookVO book) {
    	
        return 0;
    }

    // 책 정보 수정 (UPDATE book SET bookname=?, author=?, publisher=?, price=?, image=? WHERE isbn=?)
    public int update(BookVO book) {
    	
        return 0;
    }
}
