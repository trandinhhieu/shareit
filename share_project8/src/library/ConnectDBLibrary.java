package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class ConnectDBLibrary {
	
	
//	private static final String URL = "jdbc:mysql://node16432-bnews-dhieu.kilatiron.com/bnews?useUnicode=true&characterEncoding=UTF-8";
	private static final String URL = "jdbc:mysql://localhost:3306/myblog?useUnicode=true&characterEncoding=UTF-8";
	private static final String USER = "root";
//	private static final String PASSWORD = "AEAqkh23662";
	private static final String PASSWORD = "";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			// Nạp driver
			Class.forName("com.mysql.jdbc.Driver");

			// Tạo chuỗi kết nối
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(PreparedStatement pst) {
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Statement st, ResultSet rs, Connection conn) {
		close(rs);
		close(st);
		close(conn);
	}

	public static void close(PreparedStatement pst, ResultSet rs, Connection conn) {
		close(rs);
		close(pst);
		close(conn);
	}

}
