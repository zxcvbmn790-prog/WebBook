package Member;

public class MemberService {

	MemberDAO dao;

	public MemberService() {
		dao = new MemberDAOH2();
	}

	public MemberVO getLoginUser(String username, String password) {
		return dao.login(username, password);
	}

	public boolean registerMember(MemberVO mv) {
		int result = dao.register(mv);
		return result > 0; 
	}
}