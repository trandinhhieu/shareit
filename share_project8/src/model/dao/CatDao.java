package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constant.Define;
import library.ConnectDBLibrary;
import model.bean.Category;

public class CatDao {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	/**
	 * LẤY DANH SÁCH DANH MỤC
	 * 
	 * @return
	 */
	public ArrayList<Category> getItems() {
		ArrayList<Category> alCat = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM category ORDER BY id_cat DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objCat = new Category(rs.getInt("id_cat"), rs.getString("name"), rs.getInt("ParentID"));
				alCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return alCat;
	}
	
	/**
	 * LẤY DANH SÁCH DANH MỤC CON
	 * 
	 * @return
	 */
	public ArrayList<Category> getItemsChild() {
		ArrayList<Category> alCat = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM category WHERE ParentID > 0 ORDER BY id_cat DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objCat = new Category(rs.getInt("id_cat"), rs.getString("name"), rs.getInt("ParentID"));
				alCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return alCat;
	}

	/**
	 * THÊM DANH MỤC
	 * 
	 * @param objCat
	 * @return
	 */
	public int addItem(Category objCat) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "INSERT INTO category(name,ParentID) VALUES(?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getName());
			pst.setInt(2, objCat.getId_parent());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}

	/**
	 * LẤY DANH MỤC BẰNG ID
	 * 
	 * @param id
	 * @return
	 */
	public Category getItem(int id) {
		Category objCat = new Category();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM category WHERE id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				objCat = new Category(rs.getInt("id_cat"), rs.getString("name"), rs.getInt("ParentID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return objCat;
	}

	/**
	 * XÓA DANH MỤC
	 * 
	 * @param id
	 * @return
	 */
	public int delItem(int id) {
		updatePRID(id);
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "DELETE FROM category WHERE id_cat = ?";
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

	/**
	 * SỬA DANH MỤC
	 * 
	 * @param objCat
	 * @return int
	 */
	public int editItem(Category objCat) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE category SET name = ? WHERE id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getName());
			pst.setInt(2, objCat.getId_cat());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}
	
	/**
	 * Update parent_id
	 * @param id
	 * @return
	 */
	public int updatePRID(int id) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE category SET ParentID = 0 WHERE ParentID = ?";
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
	
	/**
	 * LẤY TỔNG SỐ DÒNG
	 * @return
	 */
	public int sumCount() {
		int count = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM category";
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
	 * ĐẾM
	 * @param offset
	 * @return
	 */
	public int CountID(int id) {
		int count = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM category WHERE id_cat = ?";
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

	public ArrayList<Category> getPanigation(int offset) {
		ArrayList<Category> alCat = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM category LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT);
			rs = pst.executeQuery();
			while(rs.next()){
				Category objCat = new Category(rs.getInt("id_cat"), rs.getString("name"), rs.getInt("ParentID"));
				alCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return alCat;
	}
}
