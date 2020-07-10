package inno.innovationserver.persistence;

import java.util.List;

import inno.innovationserver.domain.Datapoint;
import inno.innovationserver.domain.Heatmap;

public interface DatapointDao {

	boolean setDatapoint(Datapoint datapoint);
	
	List<Datapoint> getDatapoints(Heatmap heatmap);

}
