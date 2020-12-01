package de.tekup.gca.services;

import java.util.List;

import de.tekup.gca.entities.AppRole;
import de.tekup.gca.entities.User;

public interface UserService {

	
	public User addAccount(User user);
	public void deleteUser(Long user_id);
	public void updateUser(User user);
	public User findUser(Long user_id);
	public List<User> allUsers();
	public void resetPassword(String newPwd , Long user_id);
	public void resetPassword(String newPwd ,String oldPwd, Long user_id);
	public void activerUser(Long user_id);
	public void desactiverUser(Long user_id);
	public AppRole saveRole(AppRole role);
	public void addRoleToUser (String login, String roleName);
	public User findUserByLogin (String login);
	
	
	
	
}
