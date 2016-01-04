package ma.ensa.banque.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Email;

@Entity
public class Personne implements Serializable{
	@Override
	public String toString() {
		return "Personne [idPersonne=" + idPersonne + ", nomPersonne="
				+ nomPersonne + ", prenomPersonne=" + prenomPersonne
				+ ", telePersonne=" + telePersonne + ", adressePersonne="
				+ adressePersonne + ", emailPersonne=" + emailPersonne
				+ ", user=" + user + "]";
	}


	@Id
	@GeneratedValue
	private Long idPersonne;
	private String nomPersonne;
	private String prenomPersonne;
	private String telePersonne;
	private String adressePersonne;
	@Email
	private String emailPersonne;
	
	//Gestion Des Relations
	@OneToOne
	private User user;
	
	
	
	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Personne(String nomPersonne, String prenomPersonne,
			String telePersonne, String adressePersonne, String emailPersonne) {
		super();
		this.nomPersonne = nomPersonne;
		this.prenomPersonne = prenomPersonne;
		this.telePersonne = telePersonne;
		this.adressePersonne = adressePersonne;
		this.emailPersonne = emailPersonne;
	}

	

	public Long getIdPersonne() {
		return idPersonne;
	}


	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}


	public String getNomPersonne() {
		return nomPersonne;
	}


	public void setNomPersonne(String nomPersonne) {
		this.nomPersonne = nomPersonne;
	}


	public String getPrenomPersonne() {
		return prenomPersonne;
	}


	public void setPrenomPersonne(String prenomPersonne) {
		this.prenomPersonne = prenomPersonne;
	}


	public String getTelePersonne() {
		return telePersonne;
	}


	public void setTelePersonne(String telePersonne) {
		this.telePersonne = telePersonne;
	}


	public String getAdressePersonne() {
		return adressePersonne;
	}


	public void setAdressePersonne(String adressePersonne) {
		this.adressePersonne = adressePersonne;
	}


	public String getEmailPersonne() {
		return emailPersonne;
	}


	public void setEmailPersonne(String emailPersonne) {
		this.emailPersonne = emailPersonne;
	}





	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	



	
	

}
