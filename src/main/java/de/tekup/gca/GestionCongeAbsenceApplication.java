package de.tekup.gca;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import de.tekup.gca.entities.Absence;
import de.tekup.gca.entities.AppRole;
import de.tekup.gca.entities.Conge;
import de.tekup.gca.entities.Reclamation;
import de.tekup.gca.entities.User;
import de.tekup.gca.services.AbsenceService;
import de.tekup.gca.services.CongeService;
import de.tekup.gca.services.ReclamationService;
import de.tekup.gca.services.UserService;


@SpringBootApplication
public class GestionCongeAbsenceApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AbsenceService absenceService;
	@Autowired
	private CongeService congeService;
	@Autowired
	private ReclamationService reclamationService;
	

	public static void main(String[] args) {
		
		SpringApplication.run(GestionCongeAbsenceApplication.class, args);
		
	}
	
	@Bean 
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void run(String... args) throws Exception {
		  
		   User u = userService.addAccount(new User("baha", "123456789", "baha", "Zaghdoudi", "zagdoudi@gmail.com", "98654321", true, 30));
		   User u2 = userService.addAccount(new User("ayoub", "123456789", "ayoub", "Belgacem", "ayoub@hotmail.fr", "26456123", true, 30));
		   User u3 = userService.addAccount(new User("hayet", "123456789", "hayet", "Slimani", "hayet@hotmail.fr", "12545666", true, 30));

		   userService.saveRole(new AppRole(null, "ADMIN"));
		   userService.saveRole(new AppRole(null, "DIRECTEUR"));
		   userService.saveRole(new AppRole(null, "USER"));
		   
		   userService.addRoleToUser(u.getNom(), "USER");
		   userService.addRoleToUser(u2.getNom(), "ADMIN");
		   userService.addRoleToUser(u3.getNom(), "DIRECTEUR");
		  
		   absenceService.addAbsence(new Absence(LocalDate.of(2020, 9, 02), LocalDate.of(2020, 9, 03),u));
		   absenceService.addAbsence(new Absence(LocalDate.of(2020, 9, 05), LocalDate.of(2020, 9, 06),u2));
		   congeService.addConge(new Conge(LocalDate.of(2020, 9, 05), LocalDate.of(2020, 9, 10), "non", false, true, u));
		   congeService.addConge(new Conge(LocalDate.of(2020, 9, 05), LocalDate.of(2020, 9, 10), "non", false, true, u2));
		   
		   reclamationService.addReclamation(new Reclamation("bonjour ayoub", "bonjour", LocalDate.of(2020, 10, 01), u));
		   reclamationService.addReclamation(new Reclamation("bonjour ayoub", "bonjour", LocalDate.of(2020, 11, 01), u2));


		
		
	}
	
	 
	
	

}
