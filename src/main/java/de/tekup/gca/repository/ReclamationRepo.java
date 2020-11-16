package de.tekup.gca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.gca.entities.Reclamation;

public interface ReclamationRepo extends JpaRepository<Reclamation, Long> {

}
