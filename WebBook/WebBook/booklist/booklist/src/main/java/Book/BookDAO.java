package Book;

import java.util.List;

public interface BookDAO {
	public int save(BookVO pb);
	public List<BookVO> findAll();
	public BookVO findById(int ibns);
	public int update(BookVO pb);
	public int delete(int ibns);
	}