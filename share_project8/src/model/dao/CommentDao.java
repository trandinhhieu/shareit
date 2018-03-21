package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constant.Define;
import library.ConnectDBLibrary;
import model.bean.Comment;

public class CommentDao {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	/**
	 * LẤY DANH SÁCH COMMENT BẰNG ID_NEWS
	 */
	public ArrayList<Comment> getItems(int id_news) {
		ArrayList<Comment> alCmt = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM comment WHERE id_news = ? ORDER BY date_create DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_news);
			rs = pst.executeQuery();
			while (rs.next()) {
				Comment objCmt = new Comment(rs.getInt("id_com"), rs.getString("name"), rs.getString("email"), rs.getString("content"), rs.getTimestamp("date_create"), rs.getInt("id_news"), rs.getInt("parent_id"), rs.getInt("active"));
				alCmt.add(objCmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return alCmt;
	}
	
	/**
	 * LẤY DANH SÁCH COMMENT BẰNG ID_NEWS
	 */
	public ArrayList<Comment> getItems() {
		ArrayList<Comment> alCmt = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT *, n.picture AS picture FROM comment AS c INNER JOIN news AS n ON c.id_news = n.id_news ORDER BY date_create DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Comment objCmt = new Comment(rs.getInt("id_com"), rs.getString("name"), rs.getString("email"), rs.getString("content"), rs.getTimestamp("date_create"), rs.getInt("id_news"), rs.getInt("parent_id"), rs.getString("picture"), rs.getInt("active"));
				alCmt.add(objCmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return alCmt;
	}
	
	/**
	 * LẤY DANH SÁCH COMMENT 
	 */
	public ArrayList<Comment> getPagination(int offset) {
		ArrayList<Comment> alCmt = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT *, n.picture AS picture FROM comment AS c INNER JOIN news AS n ON c.id_news = n.id_news ORDER BY id_com DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT);
			rs = pst.executeQuery();
			while (rs.next()) {
				Comment objCmt = new Comment(rs.getInt("id_com"), rs.getString("name"), rs.getString("email"), rs.getString("content"), rs.getTimestamp("date_create"), rs.getInt("id_news"), rs.getInt("parent_id"), rs.getString("picture"), rs.getInt("active"));
				alCmt.add(objCmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return alCmt;
	}
	
	/**
	 * đếm số cmt
	 */
	public int sumCount() {
		int count = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM comment";
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
	 * LẤY DANH SÁCH COMMENT 
	 */
	public int countCmt(int id) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM comment WHERE id_news = ? AND parent_id = 0";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return result;
	}
	
	/**
	 * THÊM BÌNH LUẬN
	 */
	public int addItem(Comment objCmt) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "INSERT INTO comment(name,email,content,date_create,id_news,parent_id,active) VALUES(?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCmt.getName());
			pst.setString(2, objCmt.getEmail());
			pst.setString(3, objCmt.getContent());
			pst.setTimestamp(4, objCmt.getDate_create());
			pst.setInt(5, objCmt.getId_news());
			pst.setInt(6, objCmt.getParent_id());
			pst.setInt(7, objCmt.getActive());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}
	
	/**
	 * XÓA DANH MỤC
	 * 
	 * @param id
	 * @return
	 */
	public int delItem(int id) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "DELETE FROM comment WHERE id_com = ?";
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
	
	public int updateActive(int active, int id) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE comment SET active = ? WHERE id_com = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, active);
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
