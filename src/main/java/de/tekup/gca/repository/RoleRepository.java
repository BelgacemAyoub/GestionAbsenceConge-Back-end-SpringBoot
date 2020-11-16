package de.tekup.gca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.gca.entities.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, Long> {
	
	public AppRole findByRoleName(String roleName);

}
