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

		// 💡 SELECT * 로 변경하여 category까지 모두 가져옴
		StringBuilder sql = new StringBuilder("SELECT * FROM book");

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
					// 💡 끝에 rs.getString("category") 추가!
					BookVO book = new BookVO(rs.getInt("isbn"), rs.getString("bookname"), rs.getString("author"),
							rs.getString("publisher"), rs.getString("image"), rs.getString("price"),
							rs.getString("category"));
					list.add(book);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 1. 카테고리별로 원하는 개수만큼 잘라서 가져오기
	public List<BookVO> findByCategoryAndPage(String category, int offset, int limit, boolean viewAll) {
		List<BookVO> list = new ArrayList<BookVO>();
		StringBuilder sql = new StringBuilder("SELECT * FROM book");

		boolean hasCategory = category != null && !category.isEmpty() && !"전체".equals(category);
		if (hasCategory) {
			sql.append(" WHERE category = ?");
		}
		sql.append(" ORDER BY isbn");

		if (!viewAll) { // 전체보기가 아니면 4개씩 자름
			sql.append(" LIMIT ? OFFSET ?");
		}

		try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {
			int paramIdx = 1;
			if (hasCategory)
				ps.setString(paramIdx++, category);
			if (!viewAll) {
				ps.setInt(paramIdx++, limit);
				ps.setInt(paramIdx++, offset);
			}
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(new BookVO(rs.getInt("isbn"), rs.getString("bookname"), rs.getString("author"),
							rs.getString("publisher"), rs.getString("image"), rs.getString("price"),
							rs.getString("category")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int countByCategory(String category) {
		String sql = "SELECT COUNT(*) FROM book";
		boolean hasCategory = category != null && !"전체".equals(category);
		if (hasCategory)
			sql += " WHERE category = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			if (hasCategory)
				ps.setString(1, category);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public BookVO findByIsbn(int isbn) {
		String sql = "SELECT * FROM book WHERE isbn = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, isbn);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new BookVO(rs.getInt("isbn"), rs.getString("bookname"), rs.getString("author"),
							rs.getString("publisher"), rs.getString("image"), rs.getString("price"),
							rs.getString("category"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}