package ma.ensa.banque.service;

public interface IBanqueService {
	
	
	
	//Gestion Des Operaitions
	public void verser(double mt, Long idCompte, Long codeEmp);
	public void retirer(double mt, Long idCompte, Long codeEmp);
	public void virement(double mt, Long idCompte1, Long idCompte2,Long codeEmp);
	
	

}
