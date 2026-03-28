package WebBookStore.member;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberDAO {
	public MemberVO login(String id, String pw);

	public int register(MemberVO mv);

	public MemberVO findByName(String username);

	public int update(MemberVO member);
}