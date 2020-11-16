package de.tekup.gca.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Conge {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private String raison;
	private boolean confirmation;
	private boolean payer;
	private int SoldeConge;
	
	
	@ManyToOne
	private User user;


	public Conge(LocalDate dateDebut, LocalDate dateFin, String raison, boolean confirmation, boolean payer,
			int soldeConge, User user) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.raison = raison;
		this.confirmation = confirmation;
		this.payer = payer;
		SoldeConge = soldeConge;
		this.user = user;
	}
	
	
}
