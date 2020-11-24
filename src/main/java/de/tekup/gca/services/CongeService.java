package de.tekup.gca.services;

import java.util.List;

import de.tekup.gca.entities.Conge;

public interface CongeService {
	
	public Conge addConge (Conge conge);
	public void deleteConge (Long conge_id);
	public void updateConge (Conge conge);
	public List<Conge> allConges ();
	public Conge findConge (Long conge_id);
	public void acceptConge (Conge conge, String login);
	public void refuseConge (Long conge_id, String message);
	public List<Conge> allCongeById(Long user_id);
	
	
	

}
