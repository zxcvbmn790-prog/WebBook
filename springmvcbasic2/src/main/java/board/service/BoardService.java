package board.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.model.Board;
import board.model.BoardForm;
import board.model.BoardTable;
import board.model.PageList;
import board.repository.BoardDAO;
import member.model.User;
import member.repository.UserDAO;
@Service
public class BoardService {

	@Autowired
	BoardDAO boardDAO;

	@Autowired
	UserDAO userDAO;
	//public boolean insert(BoardForm board) {
		/*
		// form에서 데이터와 테이블에 전달되는 데이터가 다르기 때문에 일치하도록 코드를 변환시켜야 한다
		// 변환시키는 유틸 (스프링에서만 있다)
		BoardTable tboard = new BoardTable();
		BeanUtils.copyProperties(board, tboard);  //board값을 tboard로 값을 전달한다
		member.model.User user = userDAO.findByUsername(board.getAuthor());
		tboard.setUserid(user.getId());
		System.out.println(tboard);
		
		return boardDAO.save(tboard)>0? true : false;*/
		
		public boolean insert(BoardForm board) {
			//form에서 데이터와 테이블에 전달되는 데이터가 다르기 때문에 일치하도록 코드 변환
			BoardTable tboard=new BoardTable();
			BeanUtils.copyProperties(board, tboard);
			User user=userDAO.findByUsername(board.getAuthor());
			tboard.setUserid(user.getId());
			System.out.println(tboard);
			
			return boardDAO.save(tboard)>0 ? true : false;
		}

		public List<Board> getList() {
			// TODO Auto-generated method stub
			return boardDAO.findAll(0, 0);
		}

		public PageList getPageList(int requestPage) {
			PageList pageList = new PageList();
			//글의 전체 갯수 
			int totalCount=boardDAO.count(); 
			pageList.setPagePerCount(totalCount);
			
			//1페이지의 글의 갯수
			int pagePerCount=10; 
			pageList.setPagePerCount(pagePerCount);
			
			//전체페이지
			int totalPage=0; 
			totalPage=totalCount/pagePerCount; // 111/10=11 -> 12page
			if((totalCount%pagePerCount)!=0) totalPage++;
			pageList.setPagePerCount(totalPage);
			
			// 요청페이지 = 현제 페이지가 된다
			pageList.setPagePerCount(requestPage);
			
			
			//글의 시작번호, 글의 끝번호
			int startnum=(requestPage-1)*pagePerCount+1; //글의 시작번호 -> 자동계산
			int endnum=requestPage*pagePerCount; //글의 끝번호 ->자동계산
			if(endnum>totalCount) endnum=totalCount;
			//list의 보더의 리스트 는 이 두 번호를 이용할 것이다
			List<Board> list = boardDAO.findAll(startnum, endnum); // 아직까지 이 기능은 만들지 않았다
			pageList.setList(list);
			
			int startPage=1+((requestPage-1)/5*5); //네비게이트 시작번호 ->자동계산
			int endPage=startPage+5-1;//네비게이트 끝번호 ->자동계산
			if(endPage>totalPage) endPage=totalPage;
			pageList.setStartPage(startnum);
			pageList.setEndPage(endnum);

			//이전페이지 다음페이지 표시 여부 확인 속성
			boolean isPre=false;
			boolean isNext=false;
			if(endPage<=5){isPre=false;isNext=true;}
			if(endPage>5) {isPre=true;isNext=true;}
			// endPage, totalPage를 이용하여 isNext를 확인
			if(endPage==totalPage) {isPre=true;isNext=false;}
			pageList.setPre(isPre);
			pageList.setNext(isNext);
			
			return pageList;
		}
	}
	

