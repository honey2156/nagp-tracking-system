package com.nagarro.nagptrackingsystem.servicesimp;

import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.nagptrackingsystem.constant.BatchTechnology;
import com.nagarro.nagptrackingsystem.constant.Message;
import com.nagarro.nagptrackingsystem.dto.Response;
import com.nagarro.nagptrackingsystem.entity.Batch;
import com.nagarro.nagptrackingsystem.repositories.BatchRepository;
import com.nagarro.nagptrackingsystem.services.BatchService;

@Service
public class BatchServiceImp implements BatchService {

	@Autowired
	BatchRepository batchRepository;

	@Override
	@Transactional
	public Response addBatch(Batch batch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(batch.getStartDate());
			if (batch.getYear() != calendar.get(Calendar.YEAR)) {
				response = new Response(Message.BATCH_INVALID_START_DATE, "exception");
			} else if (batchRepository.findFirstByBatchName(batch.getBatchName()) != null) {
				response = new Response(Message.BATCH_EXISTS, "false");
			} else {
				Batch addedBatch = batchRepository.save(batch);
				response = new Response(addedBatch, "true");
			}
		} catch (

		Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "exception");
		}
		return response;
	}

	@Override
	@Transactional
	public Response getBatches() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		try {
			return new Response(batchRepository.findAll(), "true");
		} catch (Exception ex) {
			return new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "exception");
		}
	}

	@Override
	public Response getBatchTechnologies()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			response = new Response(EnumSet.allOf(BatchTechnology.class), "true");
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "exception");
		}
		return response;
	}

	@Override
	public Response getBatchById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			response = new Response(batchRepository.findById(id), "true");
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "exception");
		}
		return response;
	}

	@Override
	public Response deleteBatch(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			batchRepository.deleteById(id);
			response = new Response(Message.DELETE_SUCCESS, "true");
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

	@Override
	public Response editBatch(int id, BatchTechnology batchTechnology, String description, Date startDate, int year)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
