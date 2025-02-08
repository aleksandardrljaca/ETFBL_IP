package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	public static PreparedStatement prepareStatement(Connection connection,
			String sql, boolean returnGeneratedKeys, Object... values)
			throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sql,
				returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS
						: Statement.NO_GENERATED_KEYS);
		setValues(preparedStatement, values);
		return preparedStatement;
	}

	public static void setValues(PreparedStatement preparedStatement,
			Object... values) throws SQLException {
		for (int i = 0; i < values.length; i++) {
			preparedStatement.setObject(i + 1, values[i]);
		}
	}
	
	public static void close(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	public static void close(Statement s) throws SQLException {
		if (s != null) {
			s.close();
		}
	}

	public static void close(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}

	public static void close(Connection conn, Statement s) throws SQLException {
		close(s);
		close(conn);
	}

	public static void close(Connection conn, ResultSet rs) throws SQLException {
		close(rs);
		close(conn);
	}

	public static void close(Statement s, ResultSet rs) throws SQLException {
		close(rs);
		close(s);
	}

	public static void close(Connection conn, Statement s, ResultSet rs) throws SQLException {
		close(rs);
		close(s);
		close(conn);
	}
}