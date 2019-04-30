package anton.nagptrackingsystem.services;

import org.springframework.stereotype.Service;

import anton.nagptrackingsystem.constant.UserType;
import anton.nagptrackingsystem.dto.Response;
import anton.nagptrackingsystem.entity.User;

@Service
public interface UserService {

	public Response findAdmins() throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response adminById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response adminByEmail(String email)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response addAdmin(User user) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response login(String email, String password)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response deleteUser(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response updateAdmin(int id, String password, String name, String contactNo, UserType userType)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public Response getUserTypes() throws InstantiationException, IllegalAccessException, ClassNotFoundException;
}
