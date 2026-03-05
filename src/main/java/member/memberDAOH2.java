package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.BookVO;

public class memberDAOH2 implements memberDAO {
	Connection conn;
	memberVO mv;

	public memberDAOH2() {
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			System.out.println(conn);
		} catch (Exception e) {
			System.out.println("Connection ��ü ���� ����!!");
			e.printStackTrace();
		}
	}

	@Override
    public int login(String id, String pw) {
        // 수정: select 뒤에 * 를 추가하여 모든 컬럼을 가져오도록 수정
        String sql = "SELECT * FROM member WHERE id = ? AND pw = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
        	System.out.println("h2 id : " + id);
        	System.out.println("h2 pw : " + pw);
            ps.setString(1, id);
            ps.setString(2, pw);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // 실패 시 0 반환
    }
	@Override
	public int register(BookVO pb) {
		String sql = "insert into member values(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mv.getId());
			ps.setString(2, mv.getPw());
			ps.setString(3, mv.getHp());
			ps.setString(4, mv.getEmail());
			ps.setString(5, mv.getNickname());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("회원가입 입력성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
