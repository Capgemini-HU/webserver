package inno.innovationserver.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import inno.innovationserver.domain.Datapoint;
import inno.innovationserver.domain.Heatmap;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatapointPostgresDaoImpl extends PostgresBaseDao implements DatapointDao {
	private List<Datapoint> datapoints = new ArrayList<Datapoint>();
	
	public boolean setDatapoint(Datapoint datapoint) {
		boolean result = false;

		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO Datapoint(coordX, coordY, time, date) VALUES(?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, datapoint.getCoordX());
			pstmt.setInt(2, datapoint.getCoordY());
			pstmt.setTime(3, datapoint.getTime());
			pstmt.setDate(4, datapoint.getDate());
			pstmt.execute();

			result = true;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}
	
	public List<Datapoint> getDatapoints(Heatmap heatmap){
		try (Connection con = super.getConnection()) {
			String query = "SELECT * FROM datapoint WHERE date BETWEEN ? AND ? AND time BETWEEN ? AND ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setDate(1, heatmap.getFromDate());
			pstmt.setDate(2, heatmap.getToDate());
			pstmt.setTime(3, heatmap.getFromTime());
			pstmt.setTime(4, heatmap.getToTime());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int coordX = rs.getInt("coordx");
				int coordY = rs.getInt("coordy");
				Time time = rs.getTime("time");
				Date date = rs.getDate("date");
				
				Datapoint datapoint = new Datapoint(coordX, coordY, time, date);
				datapoints.add(datapoint);
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return datapoints;
	}
}
