package ma.ensa.banque.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client extends Personne {
 
	
	//Gestion Des Relations
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<Compte> comptes = new ArrayList<Compte>();

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Client(String nomPersonne, String prenomPersonne,
			String telePersonne, String adressePersonne, String emailPersonne) {
		super(nomPersonne, prenomPersonne, telePersonne, adressePersonne,
				emailPersonne);
	}



	public Collection<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}






	@Override
	public String toString() {
		
		return super.toString() + "Client [comptes=" + comptes + "]";
	}
	
	
	
	

}
