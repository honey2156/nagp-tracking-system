package com.nagarro.nagptrackingsystem.servicesimp;

import java.util.EnumSet;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.nagptrackingsystem.constant.Message;
import com.nagarro.nagptrackingsystem.constant.UserType;
import com.nagarro.nagptrackingsystem.dto.Response;
import com.nagarro.nagptrackingsystem.entity.User;
import com.nagarro.nagptrackingsystem.repositories.UserRepository;
import com.nagarro.nagptrackingsystem.services.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public Response findAdmins() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		try {
			return new Response(userRepository.findByUserType(UserType.admin), "true");
		} catch (Exception ex) {
			return new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
	}

	@Override
	@Transactional
	public Response adminById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			User admin = userRepository.findById(id).get();
			if (admin == null || admin.getUserType() != UserType.admin) {
				response = new Response(Message.ADMIN_ID_NOT_EXISTS, "false");
			} else {
				response = new Response(admin, "true");
			}
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

	@Override
	@Transactional
	public Response adminByEmail(String email)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			User admin = userRepository.findFirstByEmail(email);
			if (admin == null || admin.getUserType() != UserType.admin) {
				response = new Response(Message.ADMIN_EMAIL_NOT_EXISTS, "false");
			} else {
				response = new Response(admin, "true");
			}
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

	@Override
	@Transactional
	public Response addAdmin(User user) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			if (!(userRepository.findFirstByEmail(user.getEmail()) == null)) {
				response = new Response(Message.ADMIN_EMAIL_EXISTS, "false");
			} else if (!(userRepository.findFirstByContactNo(user.getContactNo()) == null)) {
				response = new Response(Message.ADMIN_CONTACT_EXISTS, "false");
			} else {
				User addedUser = userRepository.save(user);
				response = new Response(addedUser, "true");
			}
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

	@Override
	@Transactional
	public Response login(String email, String password)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			User user = userRepository.findFirstByEmail(email);
			if (user == null) {
				response = new Response(Message.INVALID_EMAIL, "false");
			} else {
				if (BCrypt.checkpw(password, user.getPassword())) {
					response = new Response(user, "success");
				} else {
					response = new Response(Message.INVALID_PASSWORD, "false");
				}
			}
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

	@Override
	@Transactional
	public Response deleteUser(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			userRepository.deleteById(id);
			response = new Response(Message.DELETE_SUCCESS, "true");
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

	@Override
	@Transactional
	public Response updateAdmin(int id, String password, String name, String contactNo, UserType userType)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			if (userRepository.findFirstByContactNo(contactNo) != null
					&& !(userRepository.findById(id).get().getContactNo().equals(contactNo))) {
				if (userRepository.findById(id).get().getUserType() == UserType.admin) {
					response = new Response(Message.ADMIN_CONTACT_EXISTS, "false");
				} else {
					response = new Response(Message.USER_CONTACT_EXISTS, "false");
				}
			} else {
				String dePassword = BCrypt.hashpw(password, BCrypt.gensalt());
				int affectedRows = userRepository.updateUser(dePassword, name, contactNo, String.valueOf(userType), id);
				if (affectedRows == 1) {
					response = new Response(userRepository.findById(id).get(), "true");
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
	public Response getUserTypes() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			response = new Response(EnumSet.allOf(UserType.class), "true");
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

}
