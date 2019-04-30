package anton.nagptrackingsystem.servicesimp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anton.nagptrackingsystem.constant.Message;
import anton.nagptrackingsystem.dto.Response;
import anton.nagptrackingsystem.entity.Level;
import anton.nagptrackingsystem.repositories.LevelRepository;
import anton.nagptrackingsystem.services.LevelService;

@Service
public class LevelServiceImp implements LevelService {

	@Autowired
	LevelRepository levelRepository;

	@Override
	@Transactional
	public Response addLevel(Level level)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			Level addedLevel = levelRepository.save(level);
			response = new Response(addedLevel, "true");
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

	@Override
	@Transactional
	public Response getLevels() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		try {
			return new Response(levelRepository.findAll(), "true");
		} catch (Exception ex) {
			return new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
	}

	@Override
	@Transactional
	public Response getLevelById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			response = new Response(levelRepository.findById(id), "true");
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "exception");
		}
		return response;
	}

	@Override
	@Transactional
	public Response deleteLevel(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		try {
			levelRepository.deleteById(id);
			response = new Response(Message.DELETE_SUCCESS, "true");
		} catch (Exception ex) {
			response = new Response(Message.ERROR_EXCEPTION + ex.getMessage(), "false");
		}
		return response;
	}

	@Override
	@Transactional
	public Response editLevel(int id, String name, String description, double qualificationPoints)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
