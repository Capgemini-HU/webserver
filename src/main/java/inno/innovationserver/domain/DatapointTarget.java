package inno.innovationserver.domain;

import java.util.List;

public interface DatapointTarget {

	boolean setDatapoint(Datapoint datapoint);

	List<Datapoint> getDatapoints(Heatmap heatmap);

}
