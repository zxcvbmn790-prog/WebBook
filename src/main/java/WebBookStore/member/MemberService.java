package WebBookStore.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	MemberDAO dao;

	public MemberVO getLoginUser(String username, String password) {
		return dao.login(username, password);
	}

	public boolean registerMember(MemberVO mv) {
		int result = dao.register(mv);
		return result > 0;
	}
}