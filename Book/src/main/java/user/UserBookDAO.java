package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.BookVO;

public class UserBookDAO {

    Connection conn;

    public UserBookDAO() {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            System.out.println(conn);
        } catch (Exception e) {
            System.out.println("Connection 객체 생성 오류!!");
            e.printStackTrace();
        }
    }

    // 전체 출력
    public List<BookVO> findAll() {
        String sql = "SELECT * FROM webbook";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<BookVO> list = new ArrayList<BookVO>();
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
            rs.close();
            ps.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 선택 출력
    public BookVO findById(int isbn) {
        String sql = "SELECT * FROM webbook WHERE isbn = ?";
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