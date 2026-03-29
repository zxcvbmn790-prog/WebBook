package WebBookStore.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// ★ 이 줄이 반드시 있어야 합니다!
import WebBookStore.member.MemberVO;

@Service
public class AdminService {
	@Autowired
	private AdminDAO dao;

	public AdminService() {
	}

	// ====== [도서 관리] ======
	public List<AdminVO> getBookList() {
		return dao.findAll();
	}

	public int insertBook(AdminVO book) {
		return dao.insert(book);
	}

	public boolean updateBook(AdminVO admin) {
		return dao.update(admin) > 0 ? true : false;
	}

	public int deleteBook(int isbn) {
		return dao.delete(isbn);
	}

	public AdminVO getBookById(int isbn) {
		return dao.findById(isbn);
	}

	public List<MemberVO> getMemberList() {
		return dao.findAllMembers();
	}

	public int deleteMember(String username) {
		return dao.deleteMember(username);
	}

	public MemberVO getMemberById(String username) {
		return dao.getMemberById(username);
	}

	public List<MemberVO> getMemberList(String keyword) {
		return dao.findAllMembers(keyword);
	}
}