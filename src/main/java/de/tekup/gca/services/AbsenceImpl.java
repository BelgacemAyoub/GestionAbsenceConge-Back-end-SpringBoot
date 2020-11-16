package de.tekup.gca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.gca.entities.Absence;
import de.tekup.gca.entities.User;
import de.tekup.gca.repository.AbsenceRepo;
import de.tekup.gca.repository.UserRepo;

@Service
public class AbsenceImpl implements AbsenceService {
	
	@Autowired
	AbsenceRepo absenceRepo;
	@Autowired
	UserRepo userRepo;

	@Override
	public Absence addAbsence(Absence absence) {
		
		return absenceRepo.save(absence);
		
	}

	@Override
	public void deleteAbsence(Long absence_id) {

		absenceRepo.deleteById(absence_id);
	}

	@Override
	public Absence updateAbsence(Absence absence) {
		
		return absenceRepo.save(absence);
		
	}

	@Override
	public List<Absence> allAbsences() {
		
		return absenceRepo.findAll();
	}

	@Override
	public Absence findAbsence(Long absence_id) {
		
		return absenceRepo.findById(absence_id).orElseThrow(() -> new RuntimeException("Absence not exist"));
	}

	@Override
	public List<Absence> allabsencesById(Long absence_id) {
		User u =userRepo.findById(absence_id).orElseThrow(() -> new RuntimeException("Absence not exist"));
		return u.getAbsences();
	}

}
