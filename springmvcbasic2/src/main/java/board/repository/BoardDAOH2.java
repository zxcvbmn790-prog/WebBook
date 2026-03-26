package board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import board.model.Board;
import board.model.BoardTable;
import lombok.Getter;
import member.model.User;

@Repository(value = "boardDAOH2")
@Getter
public class BoardDAOH2 implements BoardDAO {

	@Autowired
	DriverManagerDataSource ds;
	

	@Override
	public int save(BoardTable board) {
		StringBuffer sql= new StringBuffer();
		sql.append("INSERT INTO board (title, USERID, content, attachment, viewcnt, type, createdate)");		
		sql.append(" VALUES (?,?,?,?,0,'일반',CURRENT_TIMESTAMP)");
		
		try {
		PreparedStatement ps = ds.getConnection().prepareStatement(sql.toString());
		ps.setString(1,board.getTitle());
		ps.setInt(2, board.getUserid());
		ps.setString(3,board.getContent());
		ps.setString(4,board.getAttachment());
		int result = ps.executeUpdate();
		ps.close();
		return result;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			// 반드시 실행해야하는 코드를 넣어줘야 한다
			// 로그 기록
			return 0;
		}
		
	}
/*
	@Override
	public List<Board> findAll(int startnum, int endnum) {
		String sql="select b.id as b_id, b.title as b_title, b.content as b_content, b.attachment as b_attachment,
		 b.viewcnt as b_viewcnt, from board b join users u on b.userid=u.id";
		
		try {
			PreparedStatement ps
			=ds.getConnection().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			List<Board> list=new ArrayList<Board>();
			while(rs.next()) {
				Board b = new Board();
			    b.setId(rs.getInt("b.id"));
			    b.setTitle(rs.getString("b.title"));
			    //자바객체에서는 userid대신 User객체를 사용하여 id를 얻을 수 있다.
			    //b.setUserid(rs.getInt("userid")); 
			    b.setContent(rs.getString("b.content"));
			    b.setAttachment(rs.getString("b.attachment"));
			    b.setViewcnt(rs.getInt("b.viewcnt"));
			    b.setType(rs.getString("b.type"));
			    b.setCreatedate(rs.getTimestamp("b.createdate"));

			    //User 객체 생성
			    User u = new User();
			    u.setId(rs.getInt("u.id"));
			    u.setUsername(rs.getString("u.username"));
			    u.setEmail(rs.getString("u.email"));
			    u.setHp(rs.getString("u.hp"));
			    u.setNickname(rs.getString("u.nickname"));
			    
			    b.setUser(u);
				
			    list.add(b);
			}
			rs.close();
			ps.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			
		}
		
	}
*/
	/*
	@Override
	public List<Board> findAll(int startnum, int endnum) {
		String sql="select b.id b_id, b.title b_title,  b.content b_content,  b.attachment b_attachment,  b.viewcnt b_viewcnt,  b.type b_type,  b.createdate b_createdate,"
				+ " u.id u_id,  u.username u_username,  u.email u_email,  u.hp u_hp,  u.nickname u_nickname"
				+ " from board b"
				+ " join users u on b.userid = u.id";
		
		try {
			PreparedStatement ps
			=ds.getConnection().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			List<Board> list=new ArrayList<Board>();
			while(rs.next()) {
				Board b = new Board();
			    b.setId(rs.getInt("b_id"));
			    b.setTitle(rs.getString("b_title"));
			    //자바객체에서는 userid대신 User객체를 사용하여 id를 얻을 수 있다.
			    //b.setUserid(rs.getInt("userid")); 
			    b.setContent(rs.getString("b_content"));
			    b.setAttachment(rs.getString("b_attachment"));
			    b.setViewcnt(rs.getInt("b_viewcnt"));
			    b.setType(rs.getString("b_type"));
			    b.setCreatedate(rs.getTimestamp("b_createdate"));

			    //User 객체 생성
			    User u = new User();
			    u.setId(rs.getInt("u_id"));
			    u.setUsername(rs.getString("u_username"));
			    u.setEmail(rs.getString("u_email"));
			    u.setHp(rs.getString("u_hp"));
			    u.setNickname(rs.getString("u_nickname"));
			    
			    b.setUser(u);
				
			    list.add(b);
			}
			rs.close();
			ps.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			
		}
	}
	*/
	
	@Override
	public List<Board> findAll(int startnum, int endnum) {
		String sql="select * from \r\n"
				+ "(select rownum rn, b.id b_id, b.title b_title,  b.content b_content,  b.attachment b_attachment,  b.viewcnt b_viewcnt,  b.type b_type,  b.createdate b_createdate,\r\n"
				+ " u.id u_id,  u.username u_username,  u.email u_email,  u.hp u_hp,  u.nickname u_nickname\r\n"
				+ " from board b\r\n"
				+ " join users u on b.userid = u.id) t \r\n"
				+ " where rn>=? and rn<=?";
		
		try {
			PreparedStatement ps
			=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, startnum);
			ps.setInt(2, endnum);
			ResultSet rs=ps.executeQuery();
			List<Board> list=new ArrayList<Board>();
			while(rs.next()) {
				Board b = new Board();
			    b.setId(rs.getInt("b_id"));
			    b.setTitle(rs.getString("b_title"));
			    //자바객체에서는 userid대신 User객체를 사용하여 id를 얻을 수 있다.
			    //b.setUserid(rs.getInt("userid")); 
			    b.setContent(rs.getString("b_content"));
			    b.setAttachment(rs.getString("b_attachment"));
			    b.setViewcnt(rs.getInt("b_viewcnt"));
			    b.setType(rs.getString("b_type"));
			    b.setCreatedate(rs.getTimestamp("b_createdate"));

			    //User 객체 생성
			    User u = new User();
			    u.setId(rs.getInt("u_id"));
			    u.setUsername(rs.getString("u_username"));
			    u.setEmail(rs.getString("u_email"));
			    u.setHp(rs.getString("u_hp"));
			    u.setNickname(rs.getString("u_nickname"));
			    
			    b.setUser(u);
				
			    list.add(b);
			}
			rs.close();
			ps.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			
		}
	}
	
	
	@Override
	public BoardTable findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardTable findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(BoardTable board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int count() {
		String sql="select count(*) cnt from board";
		int totalCount=0;
		try {
			PreparedStatement ps=ds.getConnection().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			//if(rs.next()) totalCount=rs.getInt("count(*)");
			if(rs.next()) totalCount=rs.getInt("cnt");
			rs.close();
			ps.close();
			return totalCount;
		}catch (Exception e) {
			return totalCount;
		}
		finally {
			// TODO: handle finally clause
		}

	}

	@Override
	public int viewcntup(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Board viewBoard(int id, int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Board viewBoardById(int id, int userid) {
		// TODO Auto-generated method stub
		return null;
	}

}
