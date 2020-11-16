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
import de.tekup.gca.services.AbsenceService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping (value = "/api/absence")

public class AbsenceController {

	
	@Autowired
	private AbsenceService absenceService;
	
	@GetMapping(value = "/list")
	public List<Absence> absencelist() { //  @RequestBody Absence absence : eli jeni mel front
		return absenceService.allAbsences();
	}
	
	@GetMapping (value = "/findAbsence/{absence_id}")
	public ResponseEntity<Absence> findAbsence (@PathVariable Long absence_id) {
		try {
			   Absence a =  absenceService.findAbsence(absence_id);	
			
			return new ResponseEntity<Absence>(a, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping (value = "/deleteAbsence/{absence_id}")
	public ResponseEntity<Void> deleteAbsence (@PathVariable Long absence_id) {
		try {
			absenceService.findAbsence(absence_id);
			absenceService.deleteAbsence(absence_id);		
			
			return new ResponseEntity<Void>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping(value = "/addAbsence")
	public ResponseEntity<Absence> addAbsence(@RequestBody Absence absence) { //  @RequestBody Absence absence : eli jeni mel front
		
		try {
			Absence a = absenceService.addAbsence(absence);
			return new ResponseEntity<Absence>(a, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}	
	}
	
	@PutMapping(value = "/updateAbsence")
	public ResponseEntity<Absence> updateAbsence(@RequestBody Absence absence) { //  @RequestBody Absence absence : eli jeni mel front
		
		try {
			Absence a = absenceService.addAbsence(absence);
			return new ResponseEntity<Absence>(a, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}	
	} 
	
	@GetMapping (value = "/findAbsencesById/{absence_id}")
	public ResponseEntity<List<Absence>> findAbsencesById (@PathVariable Long absence_id) {
		try {
			   List<Absence> a =  absenceService.allabsencesById(absence_id)	;
			
			return new ResponseEntity<List<Absence>>(a, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

}
