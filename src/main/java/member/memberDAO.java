package member;

import common.BookVO;

public interface memberDAO {
	public int login(String id, String pw);
	public int register(BookVO pb);
}

