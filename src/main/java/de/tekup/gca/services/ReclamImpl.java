package de.tekup.gca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.gca.entities.Reclamation;
import de.tekup.gca.entities.User;
import de.tekup.gca.repository.ReclamationRepo;
import de.tekup.gca.repository.UserRepo;

@Service
public class ReclamImpl implements ReclamationService {
	
	@Autowired
	ReclamationRepo reclamationRepo;
	@Autowired
	UserRepo userRepo;

	@Override
	public Reclamation addReclamation(Reclamation reclamation) {
		  return reclamationRepo.save(reclamation);
		
	}

	@Override
	public void deleteReclamation(Long reclamation_id) {
		  reclamationRepo.deleteById(reclamation_id);

	}

	@Override
	public Reclamation updateReclamation(Reclamation reclamation) {
		  return reclamationRepo.save(reclamation);
		
	}

	@Override
	public List<Reclamation> allReclamation() {
		  return reclamationRepo.findAll();		
	}

	@Override
	public Reclamation findReclamation(Long reclamation_id) {
		  return reclamationRepo.findById(reclamation_id).orElseThrow(() -> new RuntimeException("reclamation not exist"));
	}

	@Override
	public List<Reclamation> allReclamationById(Long user_id) {
		  User u = userRepo.findById(user_id).orElseThrow(() -> new RuntimeException("User not exist"));
		  return u.getReclamations();
	}

}
