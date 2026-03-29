package WebBookStore.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// ★ 이 부분이 있어야 MemberVO를 인식하여 빨간줄이 뜨지 않습니다!
import WebBookStore.member.MemberVO;

@Repository
public class AdminDAO {

	@Autowired
	Connection conn;

	public AdminDAO() {
		System.out.println("dao:" + conn);
	}

	// ==========================================
	// 1. 도서 관리 (기존 코드 유지)
	// ==========================================

	// 전체 도서 목록
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

	// 도서 등록
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

	// 도서 수정
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

	// 도서 삭제
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

	// 도서 단건 조회
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

	// ==========================================
	// 2. 회원 관리 (신규 추가된 코드)
	// ==========================================

	// 전체 회원 목록 조회
	public List<MemberVO> findAllMembers() {
		String sql = "SELECT * FROM member ORDER BY id ASC";
		List<MemberVO> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// MemberDAOH2와 동일하게 DB 컬럼명('id', 'pw', 'hp')을 MemberVO 필드에 매핑합니다.
				list.add(new MemberVO(0, rs.getString("id"), // username에 id값 세팅
						rs.getString("pw"), // password에 pw값 세팅
						rs.getString("email"), // email 세팅
						rs.getString("hp"), // phone에 hp값 세팅
						rs.getString("nickname") // nickname 세팅
				));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 회원 강제 탈퇴 (삭제)
	public int deleteMember(String username) {
		String sql = "DELETE FROM member WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			// 파라미터로 넘어온 username(실제 DB의 id 컬럼)을 이용해 삭제
			ps.setString(1, username);
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	// 특정 회원 상세 정보 조회
	public MemberVO getMemberById(String username) {
		String sql = "SELECT * FROM member WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				MemberVO member = new MemberVO(0, rs.getString("id"), rs.getString("pw"), rs.getString("email"),
						rs.getString("hp"), rs.getString("nickname"));
				rs.close();
				ps.close();
				return member;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 전체 회원 목록 조회 (검색 기능 포함)
	public List<MemberVO> findAllMembers(String keyword) {
		List<MemberVO> list = new ArrayList<>();
		String sql = "";
		try {
			PreparedStatement ps = null;

			// 검색어가 있을 경우 (아이디 또는 닉네임 검색)
			if (keyword != null && !keyword.trim().isEmpty()) {
				sql = "SELECT * FROM member WHERE id LIKE ? OR nickname LIKE ? ORDER BY id ASC";
				ps = conn.prepareStatement(sql);
				ps.setString(1, "%" + keyword + "%");
				ps.setString(2, "%" + keyword + "%");
			} else {
				// 검색어가 없을 경우 (전체 조회)
				sql = "SELECT * FROM member ORDER BY id ASC";
				ps = conn.prepareStatement(sql);
			}

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new MemberVO(0, rs.getString("id"), // username
						rs.getString("pw"), // password
						rs.getString("email"), // email
						rs.getString("hp"), // phone
						rs.getString("nickname") // nickname
				));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}