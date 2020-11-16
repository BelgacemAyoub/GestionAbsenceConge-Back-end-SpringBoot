package de.tekup.gca.services;

import java.util.List;

import de.tekup.gca.entities.Reclamation;

public interface ReclamationService {

	public Reclamation addReclamation (Reclamation reclamation);
	public void deleteReclamation (Long reclamation_id);
	public Reclamation updateReclamation (Reclamation reclamation);
	public List<Reclamation> allReclamation ();
	public Reclamation findReclamation (Long reclamation_id);
	public List<Reclamation> allReclamationById(Long user_id);
}
