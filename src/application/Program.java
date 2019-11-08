package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, Birthdate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)"
					);
			
			ps.setString(1, "Vinicius Rodrigues");
			ps.setString(2, "vinicius@gmail.com");
			ps.setDate(3, new java.sql.Date(sdf.parse("08/12/1977").getTime()));
			ps.setDouble(4, 3000.0);
			ps.setInt(5, 3);
			
			int rowsAffected = ps.executeUpdate();
			
			System.out.println("Done! Rows affected: " + rowsAffected);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}
	}
}
