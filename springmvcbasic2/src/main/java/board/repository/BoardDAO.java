package board.repository;

import java.util.List;

import board.model.Board;
import board.model.BoardTable;

public interface BoardDAO {

	public int save(BoardTable board);
	public List<Board> findAll(int startnum, int endnum);
	public BoardTable findById(int id);
	public BoardTable findByUsername(String username);
	public int update(BoardTable board);
	public int delete(int id); 
	public int count(); //전체 게시물 수를 확인하기 위한 함수
	public int viewcntup(int id); //게시글을 클릭할 때 게시글의 카운트를 1증가
	public Board viewBoardById(int id,int userid);//join을 위한 함수
	Board viewBoard(int id, int userid);
}