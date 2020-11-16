package de.tekup.gca.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.gca.entities.Conge;
import de.tekup.gca.entities.User;
import de.tekup.gca.models.HolidaysCalendar;
import de.tekup.gca.repository.CongeRepo;
import de.tekup.gca.repository.UserRepo;

@Service
public class CongeImpl implements CongeService{
	
	
	@Autowired
	CongeRepo congeRepo;
	@Autowired
	UserRepo userRepo;


	 List<HolidaysCalendar> holidaysList = new ArrayList<HolidaysCalendar>() {{
		 
		 add(new HolidaysCalendar(LocalDate.of(2020,1,1),"Nouvel An"));
		 add(new HolidaysCalendar(LocalDate.of(2020,1,14), "Jour anniversaire de la Révolution tunisienne"));
		 add(new HolidaysCalendar(LocalDate.of(2020,3,20), "Fête de l’Indépendance de la Tunisie"));
		 add(new HolidaysCalendar(LocalDate.of(2020,4,9), " Jour des Martyrs"));
		 add(new HolidaysCalendar(LocalDate.of(2020,5,1), " Fête du Travail"));
		 add(new HolidaysCalendar(LocalDate.of(2020,5,24), "Congés Aïd El Fitr"));
		 add(new HolidaysCalendar(LocalDate.of(2020,5,25), "Congés Aïd El Fitr"));
		 add(new HolidaysCalendar(LocalDate.of(2020,7,25), "Fête de la République"));
		 add(new HolidaysCalendar(LocalDate.of(2020,7,31), "Aïd El Idha "));
		 add(new HolidaysCalendar(LocalDate.of(2020,8,13), "Fête de la femme"));
		 add(new HolidaysCalendar(LocalDate.of(2020,8,1), "Aïd El Idha "));
		 add(new HolidaysCalendar(LocalDate.of(2020,9,20), "Jour de l’An Hégire 1441 (Ras El Am El Hijri)"));
		 add(new HolidaysCalendar(LocalDate.of(2020,10,15), "Fête de l’évacuation"));
		 add(new HolidaysCalendar(LocalDate.of(2020,10,29), "Anniversaire du prophète Mohamed (Mouled-Mawlid)"));
		}};

	@Override
	public Conge addConge(Conge conge) {
		return congeRepo.save(conge);	
	}

	@Override
	public void deleteConge(Long conge_id) {
		congeRepo.deleteById(conge_id);
		
	}

	@Override
	public void updateConge(Conge conge) {
		congeRepo.save(conge);
		
	}

	@Override
	public List<Conge> allConges() {
		return congeRepo.findAll();
	}

	@Override
	public Conge findConge(Long conge_id) {
		return congeRepo.findById(conge_id).orElseThrow(() -> new RuntimeException("Congé not exist"));
	}

	@Override
	public void acceptConge(Conge conge) {
		int nbr = 0;
		long days = ChronoUnit.DAYS.between(conge.getDateDebut(), conge.getDateFin());
		if (conge.getSoldeConge() >= days) {
			
			for (HolidaysCalendar day : holidaysList) {
				
				if ((day.getDate().isAfter(conge.getDateDebut())) && (day.getDate().isBefore(conge.getDateFin()))){
					nbr +=1;
				}
			}
			conge.setConfirmation(true);
			conge.setSoldeConge((int) (conge.getSoldeConge()-days+nbr));
			congeRepo.save(conge);
		}	
	}

	@Override
	public void refuseConge(Long conge_id, String message) {
		Conge c = congeRepo.findById(conge_id).orElseThrow(() -> new RuntimeException("Congé not exist"));
		
		c.setConfirmation(false);
		c.setRaison(message);
		congeRepo.save(c);	
	}

	@Override
	public List<Conge> allCongeById(Long user_id) {
		User u = userRepo.findById(user_id).orElseThrow(() -> new RuntimeException("Absence not exist"));
		return u.getConges();
	}


}
