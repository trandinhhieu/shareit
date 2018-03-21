package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constant.Define;
import library.ConnectDBLibrary;
import model.bean.News;

public class NewsDao {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	/**
	 * LẤY DANH SÁCH TIN TỨC
	 * 
	 * @return
	 */
	public ArrayList<News> getItems() {
		ArrayList<News> alNews = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT *, c.name AS cname, u.fullname AS uname, u.avatar AS uavatar FROM news AS n INNER JOIN category AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user ORDER BY id_news DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getInt("slide"), rs.getString("picture"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("create_date"), rs.getInt("view"), rs.getString("cname"), rs.getInt("id_user"), rs.getInt("heart"),rs.getString("uname"),rs.getString("uavatar"));
				alNews.add(objNews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return alNews;
	}
	
	/**
	 * ĐẾM
	 * @param offset
	 * @return
	 */
	public int CountID(int id) {
		int count = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM news WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return count;
	}
	
	/**
	 * LẤY DANH SÁCH TIN TỨC
	 * 
	 * @return
	 */
	public ArrayList<News> getItemsSlide() {
		ArrayList<News> alNews = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT *, c.name AS cname, u.fullname AS uname, u.avatar AS uavatar FROM news AS n INNER JOIN category AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user WHERE slide > 0 ORDER BY id_news DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getInt("slide"), rs.getString("picture"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("create_date"), rs.getInt("view"), rs.getString("cname"), rs.getInt("id_user"), rs.getInt("heart"),rs.getString("uname"),rs.getString("uavatar"));
				alNews.add(objNews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return alNews;
	}
	
	/**
	 * Search
	 * @param name
	 * @return
	 */
	public ArrayList<News> getSearch(String name) {
		ArrayList<News> alNews = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT *, c.name AS cname, u.fullname AS uname, u.avatar AS uavatar FROM news AS n INNER JOIN category AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user WHERE n.name LIKE '%?%' ORDER BY id_news DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			while (rs.next()) {
				News objNews = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getInt("slide"), rs.getString("picture"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("create_date"), rs.getInt("view"), rs.getString("cname"), rs.getInt("id_user"), rs.getInt("heart"),rs.getString("uname"),rs.getString("uavatar"));
				alNews.add(objNews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return alNews;
	}
	
	/**
	 * HOT NEWS
	 */
	public ArrayList<News> getHotNews(int offset) {
		ArrayList<News> alNews = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT *, c.name AS cname, u.fullname AS uname, u.avatar AS uavatar FROM news AS n INNER JOIN category AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user ORDER BY view DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT);
			rs = pst.executeQuery();
			while (rs.next()) {
				News objNews = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getInt("slide"), rs.getString("picture"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("create_date"), rs.getInt("view"), rs.getString("cname"), rs.getInt("id_user"), rs.getInt("heart"),rs.getString("uname"),rs.getString("uavatar"));
				alNews.add(objNews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return alNews;
	}
	
	/**
	 * Recent News
	 */
	public ArrayList<News> getRecentNews(int offset) {
		ArrayList<News> alNews = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT *, c.name AS cname, u.fullname AS uname, u.avatar AS uavatar FROM news AS n INNER JOIN category AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user ORDER BY create_date DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT);
			rs = pst.executeQuery();
			while (rs.next()) {
				News objNews = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getInt("slide"), rs.getString("picture"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("create_date"), rs.getInt("view"), rs.getString("cname"), rs.getInt("id_user"), rs.getInt("heart"),rs.getString("uname"),rs.getString("uavatar"));
				alNews.add(objNews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return alNews;
	}
	
	/**
	 * Old News
	 */
	public ArrayList<News> getOldNews(int offset) {
		ArrayList<News> alNews = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT *, c.name AS cname, u.fullname AS uname, u.avatar AS uavatar FROM news AS n INNER JOIN category AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user ORDER BY create_date ASC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT);
			rs = pst.executeQuery();
			while (rs.next()) {
				News objNews = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getInt("slide"), rs.getString("picture"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("create_date"), rs.getInt("view"), rs.getString("cname"), rs.getInt("id_user"), rs.getInt("heart"),rs.getString("uname"),rs.getString("uavatar"));
				alNews.add(objNews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return alNews;
	}
	

	/**
	 * THÊM TIN TỨC
	 * 
	 * @param objNews
	 * @return
	 */
	public int addItem(News objNews) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "INSERT INTO news(name,id_cat,slide,picture,preview_text,detail_text,create_date,view,id_user,heart) VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNews.getName());
			pst.setInt(2, objNews.getId_cat());
			pst.setInt(3, objNews.getSlide());
			pst.setString(4, objNews.getPicture());
			pst.setString(5, objNews.getPreview_text());
			pst.setString(6, objNews.getDetail_text());
			pst.setTimestamp(7, objNews.getCreate_date());
			pst.setInt(8, objNews.getView());
			pst.setInt(9, objNews.getId_user());
			pst.setInt(10, objNews.getHeart());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}

	/**
	 * XÓA TIN TỨC
	 * 
	 * @param id
	 * @return
	 */
	public int delItem(int id) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "DELETE FROM news WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}

	 public int editItem(News objNews) {
	 int result = 0;
	 conn = ConnectDBLibrary.getConnection();
	 String sql = "UPDATE news SET name = ?, id_cat = ?, picture = ?, preview_text = ?, detail_text = ? WHERE id_news = ?";
	 try {
	 pst = conn.prepareStatement(sql);
	 pst.setString(1, objNews.getName());
	 pst.setInt(2, objNews.getId_cat());
	 pst.setString(3, objNews.getPicture());
	 pst.setString(4, objNews.getPreview_text());
	 pst.setString(5, objNews.getDetail_text());
	 pst.setInt(6, objNews.getId_news());
	 result = pst.executeUpdate();
	 } catch (SQLException e) {
	 e.printStackTrace();
	 } finally {
	 ConnectDBLibrary.close(pst, rs, conn);
	 }
	 return result;
	 }

	/**
	 * ĐẾM TỔNG SỐ TIN
	 * 
	 * @return
	 */
	public int sumCount() {
		int count = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM news";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return count;
	}
	
	public int sumSearch(String name) {
		int count = 0;
		conn = ConnectDBLibrary.getConnection();
		
		String sql = "SELECT COUNT(*) AS count FROM news WHERE name LIKE '%" + name + "%'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		
		return count;
	}
	
	/**
	 * count hot news
	 */
	public int sumHot() {
		int count = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM news ORDER BY view";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return count;
	}

	/**
	 * LẤY TIN TỨC BẰNG ID
	 * 
	 * @param nid
	 * @return
	 */
	public News getItem(int nid) {
		News objNews = new News();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT *, c.name AS cname, u.fullname AS uname, u.avatar AS uavatar FROM news AS n INNER JOIN category AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user WHERE id_news = ? ORDER BY id_news DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, nid);
			rs = pst.executeQuery();
			if (rs.next()) {
				objNews = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getInt("slide"), rs.getString("picture"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("create_date"), rs.getInt("view"), rs.getString("cname"), rs.getInt("id_user"), rs.getInt("heart"),rs.getString("uname"),rs.getString("uavatar"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return objNews;
	}

	/**
	 * LẤY DANH SÁCH TIN TỨC BẰNG ID
	 * 
	 * @param nid
	 * @return
	 */
	public ArrayList<News> getItemsbyID(int nid) {
		ArrayList<News> alNews = new ArrayList<>();
		News objNews = getItem(nid);
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT *, c.name AS cname, u.fullname AS uname, u.avatar AS uavatar FROM news AS n INNER JOIN category AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user WHERE n.id_cat = ? ORDER BY id_news DESC LIMIT 2";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objNews.getId_cat());
			rs = pst.executeQuery();
			while (rs.next()) {
				News objN = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getInt("slide"), rs.getString("picture"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("create_date"), rs.getInt("view"), rs.getString("cname"), rs.getInt("id_user"), rs.getInt("heart"),rs.getString("uname"),rs.getString("uavatar"));
				alNews.add(objN);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}

		return alNews;
	}

	/**
	 * LẤY DANH SÁCH TIN TỨC (PHÂN TRANG)
	 * 
	 * @param offset
	 * @return
	 */
	public ArrayList<News> getPanigation(int offset) {
		ArrayList<News> alNews = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT *, c.name AS cname, u.fullname AS uname, u.avatar AS uavatar FROM news AS n INNER JOIN category AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user ORDER BY id_news DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT);
			rs = pst.executeQuery();
			while (rs.next()) {
				News objN = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getInt("slide"), rs.getString("picture"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("create_date"), rs.getInt("view"), rs.getString("cname"), rs.getInt("id_user"), rs.getInt("heart"),rs.getString("uname"),rs.getString("uavatar"));
				alNews.add(objN);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return alNews;
	}
	
	public ArrayList<News> getPanigationS(int offset, String name) {
		ArrayList<News> alNews = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT *, c.name AS cname, u.fullname AS uname, u.avatar AS uavatar FROM news AS n INNER JOIN category AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user WHERE n.name LIKE '%" + name + "%' ORDER BY id_news DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT);
			rs = pst.executeQuery();
			while (rs.next()) {
				News objN = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getInt("slide"), rs.getString("picture"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("create_date"), rs.getInt("view"), rs.getString("cname"), rs.getInt("id_user"), rs.getInt("heart"),rs.getString("uname"),rs.getString("uavatar"));
				alNews.add(objN);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return alNews;
	}
	
	public ArrayList<News> getPaginationbyID(int cid, int offset) {
		ArrayList<News> alNews = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT *, c.name AS cname, u.fullname AS uname, u.avatar AS uavatar FROM news AS n INNER JOIN category AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user WHERE n.id_cat = ? ORDER BY id_news DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			pst.setInt(2, offset);
			pst.setInt(3, Define.ROW_COUNT);
			rs = pst.executeQuery();
			while (rs.next()) {
				News objNews = new News(rs.getInt("id_news"), rs.getString("name"), rs.getInt("id_cat"), rs.getInt("slide"), rs.getString("picture"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("create_date"), rs.getInt("view"), rs.getString("cname"), rs.getInt("id_user"), rs.getInt("heart"),rs.getString("uname"),rs.getString("uavatar"));
				alNews.add(objNews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return alNews;
	}

	public int sumCount(int cid) {
		int count = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM news WHERE id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return count;
	}

	public int updateSlide(int slide, int id) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE news SET slide = ? WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, slide);
			pst.setInt(2, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}

	public int updateLike(int heart, int id) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE news SET heart = ? WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, heart);
			pst.setInt(2, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}
	
	public int updateView(int id) {
		int result = 0;
		News objNews = getItem(id);
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE news SET view = ? WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objNews.getView() + 1);
			pst.setInt(2, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}

}
