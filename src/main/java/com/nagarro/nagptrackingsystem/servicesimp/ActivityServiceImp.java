package com.nagarro.nagptrackingsystem.servicesimp;

import java.util.EnumSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.nagptrackingsystem.constant.ActivityStatus;
import com.nagarro.nagptrackingsystem.constant.Message;
import com.nagarro.nagptrackingsystem.dto.Response;
import com.nagarro.nagptrackingsystem.entity.Activity;
import com.nagarro.nagptrackingsystem.entity.Batch;
import com.nagarro.nagptrackingsystem.repositories.ActivityRepository;
import com.nagarro.nagptrackingsystem.repositories.BatchRepository;
import com.nagarro.nagptrackingsystem.repositories.LevelRepository;
import com.nagarro.nagptrackingsystem.services.ActivityService;

@Service
public class ActivityServiceImp implements ActivityService {

	@Autowired
	ActivityRepository activityRepository;

	@Autowired
	BatchRepository batchRepository;

	@Autowired
	LevelRepository levelRepository;

	@Override
	@Transactional
	public Response addActivity(Activity activity)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			Activity checkActivity = activityRepository.findFirstByBatchAndLevelAndName(
					batchRepository.findById(activity.getBatch().getBatchId()).get(),
					levelRepository.findById(activity.getLevel().getLevelId()).get(), activity.getName());
			if (checkActivity != null) {
				response = new Response(Message.ACTIVITY_EXISTS, "false");
			} else {
				Activity addedActivity = activityRepository.save(activity);

				addedActivity.setBatch(batchRepository.findById(addedActivity.getBatch().getBatchId()).get());
				addedActivity.setLevel(levelRepository.findById(addedActivity.getLevel().getLevelId()).get());
				response = new Response(addedActivity, "true");
			}
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

	@Override
	@Transactional
	public Response getActivities() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		try {
			return new Response(activityRepository.findAll(), "true");
		} catch (Exception ex) {
			return new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
	}

	@Override
	@Transactional
	public Response getActivityByBatch(Batch batch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			response = new Response(activityRepository.findByBatch(batch), "true");
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

	@Override
	public Response getActivityStatus() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			response = new Response(EnumSet.allOf(ActivityStatus.class), "true");
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

}
