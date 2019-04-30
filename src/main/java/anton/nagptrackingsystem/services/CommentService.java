package anton.nagptrackingsystem.services;

import anton.nagptrackingsystem.dto.Response;

public interface CommentService {

	public Response getCommentById(int id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response deleteComment(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response getCommentsByApplicantId(int applicantId);

	// public Response editComment();
}
