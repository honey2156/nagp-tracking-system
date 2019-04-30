package anton.nagptrackingsystem.servicesimp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anton.nagptrackingsystem.constant.Message;
import anton.nagptrackingsystem.dto.Response;
import anton.nagptrackingsystem.repositories.CommentRepository;
import anton.nagptrackingsystem.services.CommentService;

@Service
public class CommentServiceImp implements CommentService {

	@Autowired
	CommentRepository commentRepository;

	@Override
	@Transactional
	public Response getCommentById(int id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			response = new Response(commentRepository.findById(id), "true");
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

	@Override
	@Transactional
	public Response deleteComment(int id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			commentRepository.deleteById(id);
			response = new Response(Message.DELETE_SUCCESS, "true");
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

	@Override
	@Transactional
	public Response getCommentsByApplicantId(int applicantId) {
		// TODO Auto-generated method stub
		return null;
	}

}
