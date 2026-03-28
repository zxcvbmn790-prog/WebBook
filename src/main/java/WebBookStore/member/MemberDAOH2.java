package WebBookStore.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOH2 implements MemberDAO {

	@Autowired
	Connection conn;

	@Override
	public MemberVO login(String username, String password) {
		// 💡 DB 컬럼명은 id와 pw로 매칭
		String sql = "SELECT * FROM member WHERE id = ? AND pw = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new MemberVO(
							0, rs.getString("id"), // DB의 'id' 컬럼 값을 MemberVO의 'username'에 넣음
							rs.getString("pw"), // DB의 'pw' 컬럼 값을 MemberVO의 'password'에 넣음
							rs.getString("email"), rs.getString("hp"),rs.getString("nickname") // DB의 'hp' 컬럼 값을 MemberVO의 'phone'에 넣음
					);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int register(MemberVO mv) {
		String sql = "INSERT INTO member (id, pw, hp, email, nickname) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, mv.getUsername()); 
			ps.setString(2, mv.getPassword()); 
			ps.setString(3, mv.getPhone());    
			ps.setString(4, mv.getEmail());
			ps.setString(5, mv.getNickname()); // 💡 VO에 추가한 닉네임 값을 DB에 넣음!

			int result = ps.executeUpdate();
			if (result > 0) {
				System.out.println("회원가입 입력 성공: " + mv.getUsername());
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}