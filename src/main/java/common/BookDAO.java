package common;

import java.util.List;

public interface BookDAO {
	public int save(BookVO pb);
	public List<BookVO> findAll();
	public BookVO findById(int id);
	public int update(BookVO pb); //id�� �����ϸ� �ش� ���̵� ���� �������� �����ϼ���.
	public int delete(int id);
}
