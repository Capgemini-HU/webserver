package inno.innovationserver.persistence;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PostgresBaseDao {
	protected final Connection getConnection() {
		Connection result = null;
		
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/postgresql");
			
			result = ds.getConnection();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		
		return result;
	}
}
