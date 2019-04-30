package anton.nagptrackingsystem.services;

import java.util.Date;

import anton.nagptrackingsystem.constant.BatchTechnology;
import anton.nagptrackingsystem.dto.Response;
import anton.nagptrackingsystem.entity.Batch;

public interface BatchService {

	public Response addBatch(Batch batch) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response getBatches() throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response getBatchTechnologies()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response getBatchById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response deleteBatch(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response editBatch(int id, BatchTechnology batchTechnology, String description, Date startDate, int year)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

}
