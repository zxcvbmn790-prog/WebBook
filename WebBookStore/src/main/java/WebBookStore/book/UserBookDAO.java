package WebBookStore.book;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserBookDAO {

    @Autowired
    private Connection conn;

    // 전체 출력
    public List<BookVO> findAll() {
        List<BookVO> list = new ArrayList<BookVO>();
        String sql = "SELECT isbn, bookname, author, publisher, image, price FROM book ORDER BY isbn";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BookVO book = new BookVO(
                        rs.getInt("isbn"),
                        rs.getString("bookname"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getString("image"),
                        rs.getString("price")
                );
                list.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 선택 출력
    public BookVO findByIsbn(int isbn) {
        String sql = "SELECT isbn, bookname, author, publisher, image, price FROM book WHERE isbn = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, isbn);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new BookVO(
                            rs.getInt("isbn"),
                            rs.getString("bookname"),
                            rs.getString("author"),
                            rs.getString("publisher"),
                            rs.getString("image"),
                            rs.getString("price")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}