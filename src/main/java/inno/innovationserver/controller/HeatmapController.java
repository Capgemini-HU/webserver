package inno.innovationserver.controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import inno.innovationserver.domain.Heatmap;
import inno.innovationserver.service.HeatmapService;

@Path("/heatmap")
public class HeatmapController {
	private HeatmapService service = new HeatmapService();
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public String test(String stringJson) throws ParseException {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		JSONObject myJson = new JSONObject(stringJson);
		String fromDate = myJson.getString("fromDate");
		String toDate = myJson.getString("toDate");
		String fromTime = myJson.getString("fromTime") + ":00";
		String toTime = myJson.getString("toTime") + ":00";
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = sdf2.parse(fromDate);
		java.sql.Date sqlFromDate = new java.sql.Date(date1.getTime());
		
		java.util.Date date2 = sdf2.parse(toDate);
		java.sql.Date sqlToDate = new java.sql.Date(date2.getTime());
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
		long ms1 = sdf1.parse(fromTime).getTime();
		Time sqlFromTime = new Time(ms1);
		
		long ms2 = sdf1.parse(toTime).getTime();
		Time sqlToTime = new Time(ms2);
		
		Heatmap heatmap = new Heatmap(sqlFromDate, sqlFromTime, sqlToDate, sqlToTime);
		boolean bool = service.getHeatmap(heatmap);
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("succes", bool);
		jab.add(job);
		
		JsonArray array = jab.build();
		return array.toString();
	}
}
