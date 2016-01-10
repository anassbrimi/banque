package ma.ensa.banque.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Operation implements Serializable{
	@Id
	@GeneratedValue
	private Long idOp;
	private String dateOperation;
	private double montant;
	private boolean statut;
	
	//Gestion Des Relations
	@ManyToOne
	private Compte compte;
	@ManyToOne
	private Employe employe;
	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Operation(String dateOperation, double montant, boolean statut) {
		super();
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.statut = statut;
	}
	
	
	public Operation(double montant, boolean statut) {
		super();
		this.montant = montant;
		this.statut = statut;
	}
	public Long getIdOp() {
		return idOp;
	}
	public void setIdOp(Long idOp) {
		this.idOp = idOp;
	}
	public String getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(String dateOperation) {
		this.dateOperation = dateOperation;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public boolean isStatut() {
		return statut;
	}
	public void setStatut(boolean statut) {
		this.statut = statut;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	
	

}
