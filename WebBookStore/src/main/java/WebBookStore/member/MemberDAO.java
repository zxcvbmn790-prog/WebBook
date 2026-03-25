package Member;

public interface MemberDAO {
	public MemberVO login(String id, String pw);
	
	public int register(MemberVO mv);
}