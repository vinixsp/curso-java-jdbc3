package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			conn = DB.getConnection();
			
			ps = conn.prepareStatement("insert into department (Name) values ('D1'),('D2')",
					Statement.RETURN_GENERATED_KEYS);
			
			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				
				while (rs.next()) {
					System.out.println("Done! Id = " + rs.getInt(1));
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}
	}
}
