package inno.innovationserver.controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONArray;
import org.json.JSONObject;

import inno.innovationserver.service.DatapointService;
import inno.innovationserver.domain.Datapoint;

@Path("/datapoint")
public class DatapointController {
	private DatapointService service = new DatapointService();
	private List<Datapoint> datapoints = new ArrayList<Datapoint>();
	
	@POST
	@Path("/storeData")
	@Consumes("application/json")
	@Produces("application/json")
	public String setDatapoints(String stringJson) throws ParseException {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		JSONObject jsonObj = new JSONObject(stringJson);
		JSONArray jsonArray = jsonObj.getJSONArray("datapoints");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			int coordX = obj.getInt("X");
			int coordY = obj.getInt("Y");
			String strTime = obj.getString("Time");
			String strDate = obj.getString("Date");
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
			long ms = sdf1.parse(strTime).getTime();
			Time sqlTime = new Time(ms);
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date date = sdf2.parse(strDate);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
			Datapoint datapoint = new Datapoint(coordX, coordY, sqlTime, sqlDate);
			datapoints.add(datapoint);
		}
		
		boolean bool = service.setDatapoints(datapoints);
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("succes", bool);
		jab.add(job);
		
		JsonArray array = jab.build();
		return array.toString();
	}
}
