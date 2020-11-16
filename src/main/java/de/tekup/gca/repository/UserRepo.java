package de.tekup.gca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.gca.entities.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
	public User findByLogin(String login);
	

}
