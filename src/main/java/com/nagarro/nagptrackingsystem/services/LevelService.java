package com.nagarro.nagptrackingsystem.services;

import org.springframework.stereotype.Service;

import com.nagarro.nagptrackingsystem.dto.Response;
import com.nagarro.nagptrackingsystem.entity.Level;

@Service
public interface LevelService {

	public Response addLevel(Level level) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response getLevels() throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response getLevelById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response deleteLevel(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response editLevel(int id, String name, String description, double qualificationPoints)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

}
