package member;

public interface memberDAO {
	public memberVO login(String id, String pw);
	
	public int register(memberVO mv);
}