package inno.innovationserver.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import inno.innovationserver.domain.Datapoint;
import inno.innovationserver.domain.DatapointTarget;
import inno.innovationserver.domain.Heatmap;
import inno.innovationserver.persistence.DbDatapointTarget;

public class HeatmapService {
	private DatapointTarget target = new DbDatapointTarget();

	public boolean getHeatmap(Heatmap heatmap) {
		boolean bool = false;
		List<Datapoint> datapoints = target.getDatapoints(heatmap);

		writeCsvFile(datapoints);
		
		try {
			String command = /*Path to python.exe*/"PATH_HERE create_heatmap.py";
			Process p = Runtime.getRuntime().exec(command, new String[0], new File(""/*Path to heatmap python project*/));
			p.waitFor();
			BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String line;
			while ((line = bri.readLine()) != null) {
				System.out.println(line);
			}
			bri.close();
			while ((line = bre.readLine()) != null) {
				System.out.println(line);
			}
			bre.close();
			p.waitFor();

			p.destroy();
			
			bool = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bool;
	}
	
	public void writeCsvFile(List<Datapoint> datapoints) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("C:\\InnovationFiles\\heatmap_points.csv");
			
			for (Datapoint datapoint : datapoints) {
				fileWriter.append(String.valueOf(datapoint.getCoordX()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(datapoint.getCoordY()));
				fileWriter.append("\n");
			}
			
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
