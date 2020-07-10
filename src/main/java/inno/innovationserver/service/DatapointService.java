package inno.innovationserver.service;

import java.util.List;

import inno.innovationserver.domain.Datapoint;
import inno.innovationserver.domain.DatapointTarget;
import inno.innovationserver.persistence.DbDatapointTarget;

public class DatapointService {
	private DatapointTarget dataTarget = new DbDatapointTarget();

	public boolean setDatapoints(List<Datapoint> datapoints) {
		// TODO Auto-generated method stub
		boolean bool = false;
		
		for (Datapoint datapoint : datapoints) {
			bool = dataTarget.setDatapoint(datapoint);
		}
		
		return bool;
	}

}
