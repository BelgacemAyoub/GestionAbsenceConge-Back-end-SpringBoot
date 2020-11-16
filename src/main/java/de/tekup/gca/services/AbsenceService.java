package de.tekup.gca.services;

import java.util.List;

import de.tekup.gca.entities.Absence;

public interface AbsenceService {
	
	public Absence addAbsence (Absence absence);
	public void deleteAbsence (Long absence_id);
	public Absence updateAbsence (Absence absence);
	public List<Absence> allAbsences ();
	public Absence findAbsence (Long absence_id);
	public List<Absence> allabsencesById(Long absence_id);

}
