package ma.ensa.banque.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;



@Entity
public class Employe extends Personne{
	
	private String fonction;
	
	//Gestion Des Relations
	@ManyToOne
	private Employe supEmpl;
	@OneToMany(mappedBy = "employe",  cascade = CascadeType.ALL)
	private Collection<Compte> comptes = new ArrayList<Compte>();
	

	public Employe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employe(String nomPersonne, String prenomPersonne,
			String telePersonne, String adressePersonne, String emailPersonne) {
		super(nomPersonne, prenomPersonne, telePersonne, adressePersonne,
				emailPersonne);
		// TODO Auto-generated constructor stub
	}
	

	public Employe(String nomPersonne, String prenomPersonne,
			String telePersonne, String adressePersonne, String emailPersonne,
			 String fonction) {
		super(nomPersonne, prenomPersonne, telePersonne, adressePersonne,
				emailPersonne);
		this.fonction = fonction;
	}

	public Employe(String fonction) {
		super();
		this.fonction = fonction;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public Employe getSupEmpl() {
		return supEmpl;
	}

	public void setSupEmpl(Employe supEmpl) {
		this.supEmpl = supEmpl;
	}
	
	
	

}
