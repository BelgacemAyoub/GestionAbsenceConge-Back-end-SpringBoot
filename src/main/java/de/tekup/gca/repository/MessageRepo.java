package de.tekup.gca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.gca.entities.Message;

public interface MessageRepo extends JpaRepository<Message, Long>{

}
