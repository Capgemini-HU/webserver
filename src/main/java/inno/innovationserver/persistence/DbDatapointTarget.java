package inno.innovationserver.persistence;

import java.util.List;

import inno.innovationserver.domain.Datapoint;
import inno.innovationserver.domain.DatapointTarget;
import inno.innovationserver.domain.Heatmap;

public class DbDatapointTarget implements DatapointTarget {
	private DatapointDao dataDao = new DatapointPostgresDaoImpl();
	
	public boolean setDatapoint(Datapoint datapoint) {
		return dataDao.setDatapoint(datapoint);
	}

	public List<Datapoint> getDatapoints(Heatmap heatmap) {
		return dataDao.getDatapoints(heatmap);
	}
}
