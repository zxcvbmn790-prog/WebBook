package member;

public class memberService {

	memberDAO dao;

	public memberService() {
		dao = new memberDAOH2();
	}

	// 로그인 정보 가져오기
	public memberVO getLoginUser(String username, String password) {
		return dao.login(username, password);
	}

	// 회원가입 처리
	public boolean registerMember(memberVO mv) {
		int result = dao.register(mv);
		return result > 0; // 1이면 true, 0이면 false
	}
}