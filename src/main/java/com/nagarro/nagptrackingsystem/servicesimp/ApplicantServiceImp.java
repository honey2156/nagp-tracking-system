package com.nagarro.nagptrackingsystem.servicesimp;

import java.util.EnumSet;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nagarro.nagptrackingsystem.constant.Message;
import com.nagarro.nagptrackingsystem.constant.NAGPStatus;
import com.nagarro.nagptrackingsystem.constant.UserType;
import com.nagarro.nagptrackingsystem.dto.Response;
import com.nagarro.nagptrackingsystem.entity.Applicant;
import com.nagarro.nagptrackingsystem.entity.Batch;
import com.nagarro.nagptrackingsystem.entity.Level;
import com.nagarro.nagptrackingsystem.entity.User;
import com.nagarro.nagptrackingsystem.repositories.ApplicantRepository;
import com.nagarro.nagptrackingsystem.repositories.BatchRepository;
import com.nagarro.nagptrackingsystem.repositories.LevelRepository;
import com.nagarro.nagptrackingsystem.repositories.UserRepository;
import com.nagarro.nagptrackingsystem.services.ApplicantService;

@Service
public class ApplicantServiceImp implements ApplicantService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ApplicantRepository applicantRepository;

	@Autowired
	BatchRepository batchRepository;

	@Autowired
	LevelRepository levelRepository;

	@Override
	// @Transactional
	public Response addApplicant(Applicant applicant)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			if (!(userRepository.findFirstByEmail(applicant.getApplicant().getEmail()) == null)) {
				response = new Response(Message.APPLICANT_EMAIL_EXISTS, "false");
			} else if (!(userRepository.findFirstByContactNo(applicant.getApplicant().getContactNo()) == null)) {
				response = new Response(Message.APPLICANT_CONTACT_EXISTS, "false");
			} else {
				User user = userRepository.save(applicant.getApplicant());
				applicant.setId(user.getUserId());
				applicant.setApplicant(user);
				Applicant addedApplicant = applicantRepository.save(applicant);
				addedApplicant.setBatch(batchRepository.findById(addedApplicant.getBatch().getBatchId()).get());
				addedApplicant.setLevel(levelRepository.findById(addedApplicant.getLevel().getLevelId()).get());
				response = new Response(addedApplicant, "true");
			}
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "exception");
			userRepository.delete(applicant.getApplicant());
		}
		return response;
	}

	@Override
	@Transactional
	public Response editApplicantByApplicant(int id, String password, String name, String contactNo)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			if (userRepository.findFirstByContactNo(contactNo) != null
					&& !(userRepository.findById(id).get().getContactNo().equals(contactNo))) {
				if (userRepository.findById(id).get().getUserType() == UserType.admin) {
					response = new Response(Message.APPLICANT_CONTACT_EXISTS, "false");
				} else {
					response = new Response(Message.USER_CONTACT_EXISTS, "false");
				}
			} else {
				String dePassword = BCrypt.hashpw(password, BCrypt.gensalt());
				int affectedRows = userRepository.updateUser(dePassword, name, contactNo, "applicant", id);
				if (affectedRows == 1) {
					response = new Response(applicantRepository.findById(id).get(), "true");
				} else {
					response = new Response(Message.UPDATE_ERROR, "false");
				}
			}
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

	@Override
	@Transactional
	public Response editApplicantByAdmin(int id, UserType userType, NAGPStatus nagpStatus, Level level, Batch batch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			response = new Response(applicantRepository.updateApplicant(String.valueOf(userType),
					String.valueOf(nagpStatus), level.getLevelId(), batch.getBatchId(), id), "true");
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

	@Override
	@Transactional
	public Response getAllApplicants() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			response = new Response(applicantRepository.findAll(), "true");
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "exception");
		}
		return response;
	}

	@Override
	@Transactional
	public Response getApplicantsPaginated(int pageNo, int pageSize)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			response = new Response(applicantRepository.findAll(PageRequest.of(pageNo - 1, pageSize)).getContent(),
					"true");
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "exception");
		}
		return response;
	}

	@Override
	public Response getNagpStatus() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			response = new Response(EnumSet.allOf(NAGPStatus.class), "true");
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

}
