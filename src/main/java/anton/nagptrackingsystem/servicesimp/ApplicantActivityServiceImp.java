package anton.nagptrackingsystem.servicesimp;

import org.springframework.stereotype.Service;

import anton.nagptrackingsystem.dto.Response;
import anton.nagptrackingsystem.entity.ApplicantActivity;
import anton.nagptrackingsystem.services.ApplicantActivityService;

@Service
public class ApplicantActivityServiceImp implements ApplicantActivityService {

	@Override
	public Response getApplicantActivities(int applicantId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getApplicantActivityById(int applicantActivityId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteApplicantActivity(int applicantActivityId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addApplicantActivity(ApplicantActivity applicantActivity)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
