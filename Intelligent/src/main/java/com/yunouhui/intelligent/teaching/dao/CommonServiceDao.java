/**
 * 项目名：student
 * 修改历史：
 * 作者： MZ
 * 创建时间： 2016年1月6日-上午9:43:21
 */
package com.yunouhui.intelligent.teaching.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yunouhui.intelligent.teaching.util.JDBCUtilsPlus;

public final class CommonServiceDao {
	public int executeUpdate(String sql) {
		int result = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtilsPlus.getConnection();
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/**
			 * 使用JDBCUtilsConfig工具类中的方法close释放资源
			 */
			JDBCUtilsPlus.colseResource(conn,ps,rs);
		}
		return result;
	}

	public int executeUpdate(String sql, Object[] obj) {
		int result = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtilsPlus.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/**
			 * 使用JDBCUtilsConfig工具类中的方法close释放资源
			 */
			JDBCUtilsPlus.colseResource(conn,ps,rs);
		}
		return result;
	}

	public ResultSet executeQuery(String sql) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtilsPlus.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/**
			 * 使用JDBCUtilsConfig工具类中的方法close释放资源
			 */
			JDBCUtilsPlus.colseResource(conn,ps,rs);
		}
		return rs;
	}

	public ResultSet executeQuery(String sql, Object[] obj) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtilsPlus.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/**
			 * 使用JDBCUtilsConfig工具类中的方法close释放资源
			 */
			JDBCUtilsPlus.colseResource(conn,ps,rs);
		}
		return rs;
	}
}
