package com.nagarro.nagptrackingsystem.services;

import com.nagarro.nagptrackingsystem.dto.Response;
import com.nagarro.nagptrackingsystem.entity.Activity;
import com.nagarro.nagptrackingsystem.entity.Batch;

public interface ActivityService {

	public Response addActivity(Activity activity)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response getActivities() throws InstantiationException, IllegalAccessException, ClassNotFoundException;;

	public Response getActivityByBatch(Batch batch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response getActivityStatus() throws InstantiationException, IllegalAccessException, ClassNotFoundException;

}
