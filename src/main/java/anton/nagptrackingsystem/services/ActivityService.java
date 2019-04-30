package anton.nagptrackingsystem.services;

import anton.nagptrackingsystem.dto.Response;
import anton.nagptrackingsystem.entity.Activity;
import anton.nagptrackingsystem.entity.Batch;

public interface ActivityService {

	public Response addActivity(Activity activity)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response getActivities() throws InstantiationException, IllegalAccessException, ClassNotFoundException;;

	public Response getActivityByBatch(Batch batch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response getActivityStatus() throws InstantiationException, IllegalAccessException, ClassNotFoundException;

}
