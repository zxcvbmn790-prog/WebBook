package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Book.BookVO;

public class AdminBookDAO {

    Connection conn;

    public AdminBookDAO() {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            System.out.println(conn);
        } catch (Exception e) {
            System.out.println("Connection 객체 생성 오류!!");
            e.printStackTrace();
        }
    }

    // 전체 목록
    public List<BookVO> findAll() {
        String sql = "SELECT * FROM book";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<BookVO> list = new ArrayList<BookVO>();
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
            rs.close();
            ps.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 등록
    public int insert(BookVO book) {
        String sql = "INSERT INTO book VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, book.getIsbn());
            ps.setString(2, book.getBookname());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getPublisher());
            ps.setString(5, book.getImage());
            ps.setString(6, book.getPrice());
            int result = ps.executeUpdate();
            ps.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 수정
    public int update(BookVO book) {
        String sql = "UPDATE book SET bookname=?, author=?, publisher=?, image=?, price=? WHERE isbn=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getBookname());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getPublisher());
            ps.setString(4, book.getImage());
            ps.setString(5, book.getPrice());
            ps.setInt(6, book.getIsbn());
            int result = ps.executeUpdate();
            ps.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 삭제
    public int delete(int isbn) {
        String sql = "DELETE FROM book WHERE isbn = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, isbn);
            int result = ps.executeUpdate();
            ps.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 단건 조회
    public BookVO findById(int isbn) {
        String sql = "SELECT * FROM book WHERE isbn = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BookVO book = new BookVO(
                        rs.getInt("isbn"),
                        rs.getString("bookname"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getString("image"),
                        rs.getString("price")
                );
                rs.close();
                ps.close();
                return book;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}