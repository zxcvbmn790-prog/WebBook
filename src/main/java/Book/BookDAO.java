package Book;

import java.util.List;

public interface BookDAO {
	public int save(BookVO pb);
	public List<BookVO> findAll();
	public BookVO findById(int id);
	public int update(BookVO pb); //id가 존재하면 해당 아이디에 대해 나머지를 수정하세요.
	public int delete(int id);
	}
