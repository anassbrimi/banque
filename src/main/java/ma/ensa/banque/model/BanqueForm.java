package ma.ensa.banque.model;

import java.util.List;

import ma.ensa.banque.entities.Client;
import ma.ensa.banque.entities.Compte;
import ma.ensa.banque.entities.Operation;

public class BanqueForm {
	
	private String code;
	private Compte compte;
	private String typeCompte;
	private String exception;
	private List<Operation> operations;
	private Client client;
	private String typeOperation;

	private double montant = 50;
	
	private Long code2;
	private String action;

	private int page = 0;
	private int nbLignes = 5;
	private int nombrePages;

	public int getPage() {
		return page;
	}
	

	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public void setPage(int page) {
		this.page = page;
	}

	public int getNbLignes() {
		return nbLignes;
	}

	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}

	public int getNombrePages() {
		return nombrePages;
	}

	public void setNombrePages(int nombrePages) {
		this.nombrePages = nombrePages;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Long getCode2() {
		return code2;
	}

	public void setCode2(Long code2) {
		this.code2 = code2;
	}

	public String getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

}
