package WebBookStore.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AdminDAOH2  {

	@Autowired
	Connection conn;

	public AdminDAOH2() {
		System.out.println("dao:"+conn);
	}
	

	public int save(AdminVO pb) {
		String sql="insert into phonebook values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, pb.getIsbn());
			ps.setString(2,pb.getBookname());
			ps.setString(3,pb.getAuthor());
			ps.setString(4,pb.getPublisher());
			ps.setString(5,pb.getImage());
			ps.setString(6,pb.getPrice());
			int result=ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}	
	}


	public List<AdminVO> findAll() {
        String sql = "SELECT * FROM book";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<AdminVO> list = new ArrayList<AdminVO>();
            while (rs.next()) {
                list.add(new AdminVO(
                        rs.getInt("isbn"),
                        rs.getString("bookname"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getString("image"),
                        rs.getString("price")
                ));
            }
            rs.close();
            ps.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


	public AdminVO findById(int id) {
		String sql="SELECT * FROM book WHERE isbn = ?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				AdminVO pb=new AdminVO(
						rs.getInt("isbn"),
                        rs.getString("bookname"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getString("image"),
                        rs.getString("price")
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


	public int update(AdminVO book) {
        String sql = "UPDATE book SET bookname=?, author=?, publisher=?, image=?, price=? WHERE isbn=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getBookname());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getPublisher());
            ps.setString(4, book.getImage());
            ps.setString(5, book.getPrice());
            ps.setInt(6, book.getIsbn());
            int result = ps.executeUpdate();
            ps.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public int delete(int isbn) {
        String sql = "DELETE FROM book WHERE isbn = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, isbn);
            int result = ps.executeUpdate();
            ps.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
