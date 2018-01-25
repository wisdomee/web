package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import bean.UserBean;
import util.DBUtil;

public class UserDAO {
	public static ResourceBundle sql = null;

	static {
		sql = DBUtil.getResourceBundle();
	}

	public static ArrayList<UserBean> selectAllUser() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<UserBean> all = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql.getString("selectAllUser"));
			rset = pstmt.executeQuery();
			all = new ArrayList<UserBean>();

			while (rset.next()) {
				UserBean user = new UserBean();
				user.setId(rset.getString(1));
				user.setPw(rset.getString(2));
				user.setNickName(rset.getString(3));
				user.setLastAccessTime(rset.getInt(4));
				all.add(user);
			}
			if (all.size() != 0) {
				return all;
			} else {
				return null;
			}
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
	}

	public static UserBean selectUser(String userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql.getString("selectUser"));
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				UserBean user = new UserBean();
				user.setId(rset.getString(1));
				user.setPw(rset.getString(2));
				user.setNickName(rset.getString(3));
				user.setLastAccessTime(rset.getInt(4));
				return user;
			}
			return null;
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
	}

	public static boolean insertUser(UserBean user) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql.getString("insertUser"));
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPw());
			pstmt.setString(3, user.getNickName());
			pstmt.setInt(4, user.getLastAccessTime());
			
			if (pstmt.executeUpdate()==1) {
				return true;
			}
			return false;
		} finally {
			DBUtil.close(conn, pstmt);
		}
	}
	
	public static boolean updateUser(String userId, int lastAccessTime) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql.getString("updateUser"));
			pstmt.setInt(1, lastAccessTime);
			pstmt.setString(2, userId);
			
			if (pstmt.executeUpdate() == 1) {
				return true;
			}
			return false;
		} finally {
			DBUtil.close(conn, pstmt);
		}
	}
	
	public static boolean deleteUser(String userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql.getString("deleteUser"));
			pstmt.setString(1, userId);
			
			if (pstmt.executeUpdate() == 1) {
				return true;
			}
			return false;
		} finally {
			DBUtil.close(conn, pstmt);
		}
	}
}