package inno.innovationserver.domain;

import java.sql.Time;
import java.sql.Date;

public class Datapoint {
	private int coordX;
	private int coordY;
	private Time time;
	private Date date;

	public Datapoint(int coordA, int coordB, Time time, Date date) {
		this.coordX = coordA;
		this.coordY = coordB;
		this.time = time;
		this.date = date;
	}
	
	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	
	public Time getTime() {
		return time;
	}
	
	public void setTime(Time time) {
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
