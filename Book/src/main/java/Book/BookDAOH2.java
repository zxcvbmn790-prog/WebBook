package Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOH2 implements BookDAO {

Connection conn;
	
	public BookDAOH2() {
		// 접속하는 주소가 고정일 경우 기본생성자에서 처리		
		try{
			Class.forName("org.h2.Driver");
			conn=DriverManager
					.getConnection("jdbc:h2:tcp://localhost/~/test","sa", "");
			System.out.println(conn);
		}catch (Exception e) {
			System.out.println("Connection 객체 생성 오류!!");
			e.printStackTrace();
		}
		
	}
	
	public BookDAOH2(String driver, String url, String username, String password) {
		// 접속하는 주소가 고정이 아닐 때는 필드 생성자를 활용		
		try{
			Class.forName(driver);
			conn=DriverManager
					.getConnection(url,username,password);
			System.out.println(conn);
		}catch (Exception e) {
			System.out.println("Connection 객체 생성 오류!!");
			e.printStackTrace();
		}
		
	}
	
	@Override
	public int save(BookVO pb) {
		String sql="insert into phonebook values(?,?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, pb.getIbns());
			ps.setString(2,pb.getBookname());
			ps.setString(3,pb.getAuthor());
			ps.setString(4,pb.getPublisher());
			ps.setString(5,pb.getImage());
			int result=ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}	
	}

	@Override
	public List<BookVO> findAll() {
		String sql="select * from phonebook"; 
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			List<BookVO> list=new ArrayList<BookVO>();
			while(rs.next()) {
				BookVO pb=new BookVO(
						rs.getInt("ibns"),
						rs.getString("bookname"), 
						rs.getString("author"), 
						rs.getString("publisher"), 
						rs.getString("image")
						);
				list.add(pb);				
			}
			rs.close();ps.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public BookVO findById(int id) {
		String sql="select * from phonebook where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				BookVO pb=new BookVO(
						rs.getInt("ibns"),
						rs.getString("bookname"), 
						rs.getString("author"), 
						rs.getString("publisher"), 
						rs.getString("image")
						);
				rs.close();ps.close();
				return pb;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int update(BookVO pb) {
		String sql="update phonebook set name=?, hp=?, email=?, memo=? where id=?";
		//update는 변경하지 않는 값은 null이므로 기존의 값으로 대체
		BookVO oldpb=findById(pb.getIbns());
		if(pb.getBookname()==null) pb.setBookname(oldpb.getBookname());
		if(pb.getAuthor()==null) pb.setAuthor(oldpb.getAuthor());
		if(pb.getPublisher()==null) pb.setPublisher(oldpb.getPublisher());
		if(pb.getImage()==null) pb.setImage(oldpb.getImage());
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,pb.getBookname());
			ps.setString(2,pb.getAuthor());
			ps.setString(3,pb.getPublisher());
			ps.setString(4,pb.getImage());
			ps.setInt(5, pb.getIbns());
			int result=ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;	
		}		
	}

	@Override
	public int delete(int id) {
		String sql="delete from phonebook where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			int result=ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}

}


