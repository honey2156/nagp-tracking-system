package anton.nagptrackingsystem.services;

import anton.nagptrackingsystem.constant.NAGPStatus;
import anton.nagptrackingsystem.constant.UserType;
import anton.nagptrackingsystem.dto.Response;
import anton.nagptrackingsystem.entity.Applicant;
import anton.nagptrackingsystem.entity.Batch;
import anton.nagptrackingsystem.entity.Level;

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
