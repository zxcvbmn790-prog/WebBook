package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class memberDAOH2 implements memberDAO {
	Connection conn;

	public memberDAOH2() {
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			System.out.println("H2 연결 성공: " + conn);
		} catch (Exception e) {
			System.out.println("Connection 객체 생성 오류!!");
			e.printStackTrace();
		}
	}

	@Override
	public memberVO login(String id, String pw) {
		String sql = "SELECT * FROM member WHERE id = ? AND pw = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, id);
			ps.setString(2, pw);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new memberVO(
							rs.getString("id"), 
							rs.getString("pw"), 
							rs.getString("hp"),
							rs.getString("email"), 
							rs.getString("nickname")
					);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // 실패 시 null
	}

	@Override
	public int register(memberVO mv) {
		String sql = "INSERT INTO member (id, pw, hp, email, nickname) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, mv.getId());
			ps.setString(2, mv.getPw());
			ps.setString(3, mv.getHp());
			ps.setString(4, mv.getEmail());
			ps.setString(5, mv.getNickname());
			
			int result = ps.executeUpdate();
			if (result > 0) {
				System.out.println("회원가입 입력 성공: " + mv.getId());
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}