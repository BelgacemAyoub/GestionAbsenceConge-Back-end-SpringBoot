package de.tekup.gca.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	@Column (unique = true)
	private String login;
	private String password;
	private String nom;
	private String prenom;
	private String mail;
	private String tel;
	private int soldeConge;
	private boolean accepted;
	

	public User(String login, String password, String nom, String prenom, String mail, String tel, boolean accepted, int soldeConge) {
		super();
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.tel = tel;
		this.soldeConge = soldeConge;
		this.accepted = accepted;
	}
	
	@ManyToMany (fetch = FetchType.EAGER)
	private List<AppRole> roles = new ArrayList<>();
	

	@OneToMany (mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JsonBackReference
	List<Absence> absences;
	
	@OneToMany (mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JsonBackReference
	List<Conge> conges;
	
	@OneToMany (mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JsonBackReference
	List<Reclamation> reclamations;
	
	@OneToMany (mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JsonBackReference
	List<Message> messages;
	
	
	

}
