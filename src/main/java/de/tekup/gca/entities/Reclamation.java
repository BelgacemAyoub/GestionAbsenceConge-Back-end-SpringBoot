package de.tekup.gca.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Reclamation {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private String message;
	private String reponse;
	private LocalDate date;
	
	
	
	public Reclamation(String message, String reponse, LocalDate date, User user) {
		super();
		this.message = message;
		this.reponse = reponse;
		this.date = date;
		this.user = user;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	private User user;
	

}
