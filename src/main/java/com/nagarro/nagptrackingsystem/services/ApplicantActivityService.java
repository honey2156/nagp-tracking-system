package com.nagarro.nagptrackingsystem.services;

import com.nagarro.nagptrackingsystem.dto.Response;
import com.nagarro.nagptrackingsystem.entity.ApplicantActivity;

public interface ApplicantActivityService {

	public Response getApplicantActivities(int applicantId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response getApplicantActivityById(int applicantActivityId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response deleteApplicantActivity(int applicantActivityId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response addApplicantActivity(ApplicantActivity applicantActivity)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	// public Response editApplicantActivity();

}
