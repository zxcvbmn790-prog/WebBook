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
public class AdminDAO {

	@Autowired
	Connection conn;

	public AdminDAO() {
		System.out.println("dao:" + conn);
	}

	// 전체 목록
	public List<AdminVO> findAll() {
		String sql = "SELECT * FROM book";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<AdminVO> list = new ArrayList<AdminVO>();
			while (rs.next()) {
				list.add(new AdminVO(rs.getInt("isbn"), rs.getString("bookname"), rs.getString("author"),
						rs.getString("publisher"), rs.getString("image"), rs.getString("price")));
			}
			rs.close();
			ps.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 등록
	public int insert(AdminVO book) {
		String sql = "INSERT INTO book VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, book.getIsbn());
			ps.setString(2, book.getBookname());
			ps.setString(3, book.getAuthor());
			ps.setString(4, book.getPublisher());
			ps.setString(5, book.getImage());
			ps.setString(6, book.getPrice());
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	// 수정
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
		String sqlCart = "DELETE FROM cart WHERE isbn = ?";
		String sqlBook = "DELETE FROM book WHERE isbn = ?";

		try {
			PreparedStatement psCart = conn.prepareStatement(sqlCart);
			psCart.setInt(1, isbn);
			psCart.executeUpdate();
			psCart.close();

			PreparedStatement psBook = conn.prepareStatement(sqlBook);
			psBook.setInt(1, isbn);
			int result = psBook.executeUpdate();
			psBook.close();

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	// 단건 조회
	public AdminVO findById(int isbn) {
		String sql = "SELECT * FROM book WHERE isbn = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, isbn);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				AdminVO book = new AdminVO(rs.getInt("isbn"), rs.getString("bookname"), rs.getString("author"),
						rs.getString("publisher"), rs.getString("image"), rs.getString("price"));
				rs.close();
				ps.close();
				return book;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 판매 요약
	public SalesSummaryVO getSalesSummary() {
	    String sql = "SELECT "
	            + "COALESCE(SUM(total_price), 0) AS total_sales, "
	            + "COUNT(*) AS total_orders, "
	            + "COALESCE(SUM(amount), 0) AS total_quantity, "
	            + "COALESCE(SUM(CASE "
	            + "WHEN FORMATDATETIME(order_date, 'yyyy-MM-dd') = FORMATDATETIME(CURRENT_TIMESTAMP, 'yyyy-MM-dd') "
	            + "THEN total_price ELSE 0 END), 0) AS today_sales "
	            + "FROM orders";

	    try {
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            SalesSummaryVO summary = new SalesSummaryVO();
	            summary.setTotalSales(rs.getInt("total_sales"));
	            summary.setTotalOrders(rs.getInt("total_orders"));
	            summary.setTotalQuantity(rs.getInt("total_quantity"));
	            summary.setTodaySales(rs.getInt("today_sales"));

	            rs.close();
	            ps.close();
	            return summary;
	        }

	        rs.close();
	        ps.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return new SalesSummaryVO(0, 0, 0, 0);
	}
	
	// 최근 7일 일자별 매출
	public List<DailySalesVO> getDailySalesList() {
	    String sql = "SELECT "
	            + "FORMATDATETIME(order_date, 'yyyy-MM-dd') AS order_day, "
	            + "COALESCE(SUM(total_price), 0) AS sales_amount, "
	            + "COUNT(*) AS order_count "
	            + "FROM orders "
	            + "GROUP BY FORMATDATETIME(order_date, 'yyyy-MM-dd') "
	            + "ORDER BY order_day DESC "
	            + "LIMIT 7";

	    List<DailySalesVO> list = new ArrayList<>();

	    try {
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            DailySalesVO vo = new DailySalesVO();
	            vo.setOrderDay(rs.getString("order_day"));
	            vo.setSalesAmount(rs.getInt("sales_amount"));
	            vo.setOrderCount(rs.getInt("order_count"));
	            list.add(vo);
	        }

	        rs.close();
	        ps.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	// 많이 팔린 책 TOP 5
	public List<BookSalesVO> getTopBookSalesList() {
	    String sql = "SELECT "
	            + "isbn, bookname, "
	            + "COALESCE(SUM(amount), 0) AS total_quantity, "
	            + "COALESCE(SUM(total_price), 0) AS total_sales "
	            + "FROM orders "
	            + "GROUP BY isbn, bookname "
	            + "ORDER BY total_quantity DESC, total_sales DESC "
	            + "LIMIT 5";

	    List<BookSalesVO> list = new ArrayList<>();

	    try {
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            BookSalesVO vo = new BookSalesVO();
	            vo.setIsbn(rs.getInt("isbn"));
	            vo.setBookname(rs.getString("bookname"));
	            vo.setTotalQuantity(rs.getInt("total_quantity"));
	            vo.setTotalSales(rs.getInt("total_sales"));
	            list.add(vo);
	        }

	        rs.close();
	        ps.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
}
