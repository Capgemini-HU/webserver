package inno.innovationserver.domain;

import java.sql.Date;
import java.sql.Time;

public class Heatmap {
	private Date fromDate;
	private Time fromTime;
	private Date toDate;
	private Time toTime;
	
	public Heatmap(Date fromDate, Time fromTime, Date toDate, Time toTime) {
		this.fromDate = fromDate;
		this.fromTime = fromTime;
		this.toDate = toDate;
		this.toTime = toTime;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Time getFromTime() {
		return fromTime;
	}

	public void setFromTime(Time fromTime) {
		this.fromTime = fromTime;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Time getToTime() {
		return toTime;
	}

	public void setToTime(Time toTime) {
		this.toTime = toTime;
	}
	
}
