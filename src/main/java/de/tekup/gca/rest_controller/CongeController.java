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

import de.tekup.gca.entities.Conge;
import de.tekup.gca.services.CongeService;

@RestController
@RequestMapping(value="/api/conge")
@CrossOrigin(origins = "http://localhost:4200")
public class CongeController {
	
	@Autowired
	private CongeService congeService;

	@PostMapping(value="/accept/{login}")
	public ResponseEntity<Conge> acceptConge(@RequestBody Conge conge, @PathVariable String login) {  
		// objet conge récupérer à partir de formulaire et login récupérer à partir de path  
		try {
			congeService.acceptConge(conge, login);
			return new ResponseEntity<Conge>(conge,HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	
	@GetMapping(value = "/list")
	public List<Conge> userlist() { 
		
		return congeService.allConges();
	}
	
	@GetMapping (value = "/findConge/{conge_id}")
	public ResponseEntity<Conge> findConge (@PathVariable Long conge_id) {
		try {
			   Conge c =  congeService.findConge(conge_id);	
			
			return new ResponseEntity<Conge>(c, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	@DeleteMapping (value = "/deleteConge/{conge_id}")
	public ResponseEntity<Void> deleteConge (@PathVariable Long conge_id) {
		try {
			    congeService.findConge(conge_id);
				congeService.deleteConge(conge_id);		
			
			return new ResponseEntity<Void>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping(value = "/addConge")
	public ResponseEntity<Conge> addConge(@RequestBody Conge conge) { //  @RequestBody Conge conge : eli jeni mel front
		
		try {
			Conge c = congeService.addConge(conge);
			return new ResponseEntity<Conge>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}	
	}
	
	@PutMapping(value = "/updateConge")
	public ResponseEntity<Void> updateConge(@RequestBody Conge conge) { //  @RequestBody Conge conge : eli jeni mel front
		
		try {
			congeService.addConge(conge);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}	
	} 
	
	
	@GetMapping (value = "/findCongesById/{user_id}")
	public ResponseEntity<List<Conge>> findAbsencesById (@PathVariable Long user_id) {
		try {
			   List<Conge> c =  congeService.allCongeById(user_id);
			
			return new ResponseEntity<List<Conge>>(c, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
