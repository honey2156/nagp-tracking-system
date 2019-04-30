package com.nagarro.nagptrackingsystem.services;

import com.nagarro.nagptrackingsystem.constant.NAGPStatus;
import com.nagarro.nagptrackingsystem.constant.UserType;
import com.nagarro.nagptrackingsystem.dto.Response;
import com.nagarro.nagptrackingsystem.entity.Applicant;
import com.nagarro.nagptrackingsystem.entity.Batch;
import com.nagarro.nagptrackingsystem.entity.Level;

public interface ApplicantService {

	public Response addApplicant(Applicant applicant)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response editApplicantByApplicant(int id, String password, String name, String contactNo)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response editApplicantByAdmin(int id, UserType userType, NAGPStatus nagpStatus, Level level, Batch batch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response getAllApplicants() throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response getApplicantsPaginated(int pageNo, int pageSize)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response getNagpStatus() throws InstantiationException, IllegalAccessException, ClassNotFoundException;

}
