package de.tekup.gca.rest_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.gca.entities.Reclamation;
import de.tekup.gca.services.ReclamationService;

@RestController
@RequestMapping ("/api/reclamation")
public class ReclamationController {
	
	@Autowired
	ReclamationService reclamationService;
	
	@GetMapping(value = "/list")
	public List<Reclamation> reclamationlist() { //  @RequestBody Absence absence : eli jeni mel front
		
		return reclamationService.allReclamation();
	}
	
	@GetMapping (value = "/findReclamation/{reclamation_id}")
	public ResponseEntity<Reclamation> findReclamation (@PathVariable Long reclamation_id) {
		try {
			Reclamation r =  reclamationService.findReclamation(reclamation_id);	
			
			return new ResponseEntity<Reclamation>(r, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping (value = "/deleteReclamation/{reclamation_id}")
	public ResponseEntity<Void> deleteReclamation (@PathVariable Long reclamation_id) {
		try {
			reclamationService.findReclamation(reclamation_id);
			reclamationService.deleteReclamation(reclamation_id);		
			
			return new ResponseEntity<Void>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping(value = "/addReclamation")
	public ResponseEntity<Reclamation> addReclamtion(@RequestBody Reclamation reclamation) { //  @RequestBody Absence absence : eli jeni mel front
		
		try {
			Reclamation r = reclamationService.addReclamation(reclamation);
			return new ResponseEntity<Reclamation>(r, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}	
	}
	
	@PutMapping(value = "/updateReclamation")
	public ResponseEntity<Reclamation> updateReclamation(@RequestBody Reclamation reclamation) { //  @RequestBody Absence absence : eli jeni mel front
		
		try {
			Reclamation r = reclamationService.addReclamation(reclamation);
			return new ResponseEntity<Reclamation>(r, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}	
	} 
	
	@GetMapping (value = "/findReclamationsById/{reclamation_id}")
	public ResponseEntity<List<Reclamation>> findReclamationById (@PathVariable Long reclamation_id) {
		try {
			   List<Reclamation> r =  reclamationService.allReclamationById(reclamation_id)	;
			
			return new ResponseEntity<List<Reclamation>>(r, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping (value = "/reclamationResponse")
	public ResponseEntity<Reclamation> reclamationResponse(@RequestBody Reclamation reclamation) {
		try {
			   Reclamation r =  reclamationService.addReclamation(reclamation);
			
			return new ResponseEntity<Reclamation>(r, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
