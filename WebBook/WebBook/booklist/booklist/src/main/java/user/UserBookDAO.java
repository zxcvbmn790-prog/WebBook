package user;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Book.BookVO;

public class UserBookDAO {

    private Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
    }

    public List<BookVO> findAll() {
        String sql = "SELECT * FROM book";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<BookVO> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new BookVO(
                        rs.getInt("isbn"),
                        rs.getString("bookname"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getString("image"),
                        rs.getString("price")
                ));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BookVO findById(int isbn) {
        String sql = "SELECT * FROM book WHERE isbn = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

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