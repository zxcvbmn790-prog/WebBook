package Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOH2 implements BookDAO {

    Connection conn;

    public BookDAOH2() {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            System.out.println(conn);
        } catch (Exception e) {
            System.out.println("Connection 객체 생성 실패!!");
            e.printStackTrace();
        }
    }

    public BookDAOH2(String driver, String url, String username, String password) {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println(conn);
        } catch (Exception e) {
            System.out.println("Connection 객체 생성 실패!!");
            e.printStackTrace();
        }
    }

    @Override
    public int save(BookVO pb) {
        String sql = "INSERT INTO book VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pb.getIsbn());
            ps.setString(2, pb.getBookname());
            ps.setString(3, pb.getAuthor());
            ps.setString(4, pb.getPublisher());
            ps.setString(5, pb.getImage());
            ps.setString(6, pb.getPrice());
            int result = ps.executeUpdate();
            ps.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<BookVO> findAll() {
        String sql = "SELECT * FROM book";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<BookVO> list = new ArrayList<BookVO>();
            while (rs.next()) {
                BookVO pb = new BookVO(
                        rs.getInt("isbn"),
                        rs.getString("bookname"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getString("image"),
                        rs.getString("price")
                );
                list.add(pb);
            }
            rs.close();
            ps.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BookVO findById(int id) {
        String sql = "SELECT * FROM book WHERE isbn = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BookVO pb = new BookVO(
                        rs.getInt("isbn"),
                        rs.getString("bookname"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getString("image"),
                        rs.getString("price")
                );
                rs.close();
                ps.close();
                return pb;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int update(BookVO pb) {
        String sql = "UPDATE book SET bookname=?, author=?, publisher=?, image=?, price=? WHERE isbn=?";
        // update에서 넘어오지 않는 값은 null이므로 기존 데이터로 대체
        BookVO oldpb = findById(pb.getIsbn());
        if (oldpb == null) return 0;
        if (pb.getBookname() == null)  pb.setBookname(oldpb.getBookname());
        if (pb.getAuthor() == null)    pb.setAuthor(oldpb.getAuthor());
        if (pb.getPublisher() == null) pb.setPublisher(oldpb.getPublisher());
        if (pb.getImage() == null)     pb.setImage(oldpb.getImage());
        if (pb.getPrice() == null)     pb.setPrice(oldpb.getPrice());
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pb.getBookname());
            ps.setString(2, pb.getAuthor());
            ps.setString(3, pb.getPublisher());
            ps.setString(4, pb.getImage());
            ps.setString(5, pb.getPrice());
            ps.setInt(6, pb.getIsbn());    // WHERE isbn=?
            int result = ps.executeUpdate();
            ps.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM book WHERE isbn = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            ps.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}