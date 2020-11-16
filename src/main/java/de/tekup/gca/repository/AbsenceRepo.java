package de.tekup.gca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.gca.entities.Absence;

public interface AbsenceRepo extends JpaRepository<Absence, Long>{

}
