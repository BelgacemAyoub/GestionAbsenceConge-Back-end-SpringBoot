package de.tekup.gca.rest_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.gca.entities.Absence;
import de.tekup.gca.entities.User;
import de.tekup.gca.entities.UserPassword;
import de.tekup.gca.models.RoleLoginModel;
import de.tekup.gca.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;


	@PostMapping(value = "/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user) { //  @RequestBody User user : eli jeni mel front
		
		try {
			userService.addAccount(user);
			
			return new ResponseEntity<User>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}	
	}

	@GetMapping(value = "/list")
	public List<User> userlist() { //  @RequestBody User user : eli jeni mel front
		
		return userService.allUsers();
		
	}
	
	@DeleteMapping(value = "/deleteUser/{user_id}")
	public ResponseEntity<Void> deleteUser (@PathVariable Long user_id) {
		try {
			    userService.findUser(user_id);
				userService.deleteUser(user_id);		
			
			return new ResponseEntity<Void>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping(value = "/updateUser")
	public ResponseEntity<Void> updateUser(@RequestBody User user) { //  @RequestBody User user : eli jeni mel front
		
		try {
			userService.addAccount(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}	
	} 
	
	@GetMapping(value = "/findUser/{user_id}")
	public ResponseEntity<User> findUser(@PathVariable Long user_id) { //  @RequestBody User user : eli jeni mel front
		try {
			User u = userService.findUser(user_id);
			
			System.out.println("******************");
			
			List<Absence> abs = u.getAbsences();
			
			for (Absence absence : abs) {
				System.out.println(absence.getId());
				System.out.println(absence.getDateDebut());
				System.out.println(absence.getDateFin());
			}

			 return new ResponseEntity<User>(u, HttpStatus.ACCEPTED);
			 

		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping(value = "/resetPass")
	public ResponseEntity<User> resetPass(@RequestBody UserPassword user) { //  @RequestBody User user : eli jeni mel front
		try {
			 userService.resetPassword(user.getNewPassword(), user.getId());
			 return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(value = "/findUserByLogin/{login}")
	public ResponseEntity<User> findUserByLogin(@PathVariable String login) { //  @RequestBody User user : eli jeni mel front
		try {
			User u = userService.findUserByLogin(login);
			
			 return new ResponseEntity<User>(u, HttpStatus.ACCEPTED);
			 

		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@PostMapping(value = "/resetUserPass")
	public ResponseEntity<User> userResetPass(@RequestBody UserPassword user) { //  @RequestBody User user : eli jeni mel front
		try {
			 userService.resetPassword(user.getNewPassword(), user.getOldPassword(),user.getId());
			 return new ResponseEntity<User>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}	
	
	@GetMapping(value = "/activerUser/{user_id}")
	public ResponseEntity<User> activerUser(@PathVariable Long user_id) {
		try {
			 userService.activerUser(user_id);
			 return new ResponseEntity<User>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(value = "/desactiverUser/{user_id}")
	public ResponseEntity<User> desactiverUser(@PathVariable Long user_id) {
		try {
			 userService.desactiverUser(user_id);
			 return new ResponseEntity<User>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping(value = "/addRoleToUser")
	public ResponseEntity<User> userResetPass(@RequestBody RoleLoginModel userRole) {
		try {
			 userService.addRoleToUser(userRole.getLogin(), userRole.getRoleName());
			 return new ResponseEntity<User>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
