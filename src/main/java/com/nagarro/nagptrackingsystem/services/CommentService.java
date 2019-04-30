package com.nagarro.nagptrackingsystem.services;

import com.nagarro.nagptrackingsystem.dto.Response;

public interface CommentService {

	public Response getCommentById(int id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response deleteComment(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response getCommentsByApplicantId(int applicantId);

	// public Response editComment();
}
