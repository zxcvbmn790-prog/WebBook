package WebBookStore.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAO {

	@Autowired
	Connection conn;

	public AdminDAO() {
		System.out.println("dao:" + conn);
	}

	// 전체 목록
	public List<AdminVO> findAll() {
		String sql = "SELECT * FROM book";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<AdminVO> list = new ArrayList<AdminVO>();
			while (rs.next()) {
				list.add(new AdminVO(rs.getInt("isbn"), rs.getString("bookname"), rs.getString("author"),
						rs.getString("publisher"), rs.getString("image"), rs.getString("price")));
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
	public int insert(AdminVO book) {
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
	public int update(AdminVO book) {
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

	public int delete(int isbn) {
		String sqlCart = "DELETE FROM cart WHERE isbn = ?";
		String sqlBook = "DELETE FROM book WHERE isbn = ?";

		try {
			PreparedStatement psCart = conn.prepareStatement(sqlCart);
			psCart.setInt(1, isbn);
			psCart.executeUpdate();
			psCart.close();

			PreparedStatement psBook = conn.prepareStatement(sqlBook);
			psBook.setInt(1, isbn);
			int result = psBook.executeUpdate();
			psBook.close();

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	// 단건 조회
	public AdminVO findById(int isbn) {
		String sql = "SELECT * FROM book WHERE isbn = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, isbn);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				AdminVO book = new AdminVO(rs.getInt("isbn"), rs.getString("bookname"), rs.getString("author"),
						rs.getString("publisher"), rs.getString("image"), rs.getString("price"));
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
