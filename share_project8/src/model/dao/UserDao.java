package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constant.Define;
import library.ConnectDBLibrary;
import model.bean.User;

public class UserDao {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	/**
	 * Lấy danh sách người dùng
	 * 
	 * @return {@link ArrayList}
	 */
	public ArrayList<User> getItems() {
		ArrayList<User> alUser = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM users ORDER BY id_user DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				User objUser = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("fullname"),
						rs.getInt("role"), rs.getString("password"), rs.getString("email"), rs.getInt("active"),
						rs.getString("avatar"));
				alUser.add(objUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, rs, conn);
		}
		return alUser;
	}

	/**
	 * Thêm người dùng
	 * 
	 * @param objUser
	 * @return int
	 */
	public int addItem(User objUser) {

		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "INSERT INTO users(username,fullname,role,password,email,active,avatar) VALUES(?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objUser.getUname());
			pst.setString(2, objUser.getUfullname());
			pst.setInt(3, objUser.getRole());
			pst.setString(4, objUser.getUpass());
			pst.setString(5, objUser.getEmail());
			pst.setInt(6, objUser.getActive());
			pst.setString(7, objUser.getAvatar());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}

	public User checkUsername(String username) {
		User objUser = null;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM users WHERE username = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()) {
				objUser = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("fullname"),
						rs.getInt("role"), rs.getString("password"), rs.getString("email"), rs.getInt("active"),
						rs.getString("avatar"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return objUser;
	}

	public User getItem(int uid) {
		User objUser = null;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM users WHERE id_user = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			rs = pst.executeQuery();
			if (rs.next()) {
				objUser = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("fullname"),
						rs.getInt("role"), rs.getString("password"), rs.getString("email"), rs.getInt("active"),
						rs.getString("avatar"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return objUser;
	}

	public int editUser(User objUser) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE users SET  fullname = ?, password = ?, email = ?, avatar = ? WHERE id_user = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objUser.getUfullname());
			pst.setString(2, objUser.getUpass());
			pst.setString(3, objUser.getEmail());
			pst.setString(4, objUser.getAvatar());
			pst.setInt(5, objUser.getUid());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}

	public int delItem(int uid) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "DELETE FROM users WHERE id_user = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return result;
	}

	public User getItem(User objUser) {
		User user = null;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM users WHERE username = ? && password = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objUser.getUname());
			pst.setString(2, objUser.getUpass());
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("fullname"),
						rs.getInt("role"), rs.getString("password"), rs.getString("email"), rs.getInt("active"),
						rs.getString("avatar"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return user;
	}

	/**
	 * LẤY TỔNG SỐ DÒNG
	 * 
	 * @return
	 */
	public int sumCount() {
		int count = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM users";
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

	public ArrayList<User> getPanigation(int offset) {
		ArrayList<User> alUser = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM users ORDER BY id_user DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT);
			rs = pst.executeQuery();
			while (rs.next()) {
				User objUser = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("fullname"),
						rs.getInt("role"), rs.getString("password"), rs.getString("email"), rs.getInt("active"),
						rs.getString("avatar"));
				alUser.add(objUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, rs, conn);
		}
		return alUser;
	}
	
	public int updateActive(int active, int id) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE users SET active = ? WHERE id_user = ?";
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
