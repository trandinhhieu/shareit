package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constant.Define;
import library.ConnectDBLibrary;
import model.bean.Ads;
import model.bean.Category;
import model.bean.Ads;

public class ADsDao {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	/**
	 * LẤY DANH SÁCH Ads 
	 */
	public ArrayList<Ads> getItems() {
		ArrayList<Ads> alAD = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM Ads ORDER BY id_ad DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Ads objAD = new Ads(rs.getInt("id_ad"), rs.getString("name"), rs.getString("picture"), rs.getString("link"),rs.getInt("active"));
				alAD.add(objAD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return alAD;
	}
	
	/**
	 * LẤY TỔNG SỐ DÒNG
	 * @return
	 */
	public int sumCount() {
		int count = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM ads";
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
	
	public ArrayList<Ads> getPanigation(int offset) {
		ArrayList<Ads> alAd = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM ads LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT);
			rs = pst.executeQuery();
			while(rs.next()){
				Ads objAD = new Ads(rs.getInt("id_ad"), rs.getString("name"), rs.getString("picture"), rs.getString("link"),rs.getInt("active"));
				alAd.add(objAD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return alAd;
	}
	
	/**
	 * THÊM Quảng cáo
	 * 
	 * @param objCat
	 * @return
	 */
	public int addItem(Ads objAD) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "INSERT INTO ads(name,picture,link,active) VALUES(?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objAD.getName());
			pst.setString(2, objAD.getPicture());
			pst.setString(3, objAD.getLink());
			pst.setInt(4, objAD.getActive());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}
	
	/**
	 * SỬA quảng cáo
	 * 
	 * @param objCat
	 * @return int
	 */
	public int editItem(Ads objAD) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE ads SET name = ?, picture = ?, link = ? WHERE id_ad = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objAD.getName());
			pst.setString(2, objAD.getPicture());
			pst.setString(3, objAD.getLink());
			pst.setInt(4, objAD.getId_ad());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}
	
	/**
	 * LẤY quảng cáo BẰNG ID
	 * 
	 * @param id
	 * @return
	 */
	public Ads getItem(int id) {
		Ads objAD = new Ads();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM ads WHERE id_ad = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				objAD = new Ads(rs.getInt("id_ad"), rs.getString("name"), rs.getString("picture"), rs.getString("link"), rs.getInt("active"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return objAD;
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
		String sql = "DELETE FROM ads WHERE id_ad = ?";
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
		String sql = "UPDATE ads SET active = ? WHERE id_ad = ?";
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
