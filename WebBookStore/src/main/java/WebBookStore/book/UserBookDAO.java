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

	// 전체 출력 (+ 검색 기능 추가)
	public List<BookVO> findAll(String searchType, String keyword) {
		List<BookVO> list = new ArrayList<BookVO>();

		StringBuilder sql = new StringBuilder("SELECT isbn, bookname, author, publisher, image, price FROM book");

		boolean isSearch = keyword != null && !keyword.trim().isEmpty();

		if (isSearch) {
			if ("title".equals(searchType)) {
				sql.append(" WHERE bookname LIKE ?");
			} else if ("author".equals(searchType)) {
				sql.append(" WHERE author LIKE ?");
			}
		}

		sql.append(" ORDER BY isbn");

		try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {

			if (isSearch) {
				ps.setString(1, "%" + keyword + "%");
			}

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					BookVO book = new BookVO(rs.getInt("isbn"), rs.getString("bookname"), rs.getString("author"),
							rs.getString("publisher"), rs.getString("image"), rs.getString("price") // 원래 쓰시던 String 타입
																									// 유지
					);
					list.add(book);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public BookVO findByIsbn(int isbn) {
		String sql = "SELECT isbn, bookname, author, publisher, image, price FROM book WHERE isbn = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, isbn);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new BookVO(rs.getInt("isbn"), rs.getString("bookname"), rs.getString("author"),
							rs.getString("publisher"), rs.getString("image"), rs.getString("price"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}