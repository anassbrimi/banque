package ma.ensa.banque.service;

import java.util.List;

import ma.ensa.banque.entities.Client;
import ma.ensa.banque.entities.Compte;
import ma.ensa.banque.entities.Employe;
import ma.ensa.banque.entities.Operation;
import ma.ensa.banque.entities.Personne;
import ma.ensa.banque.entities.Role;
import ma.ensa.banque.entities.User;

public interface IBanqueService {
	
	
	
	//Gestion Des Personnes
		public Personne readPersonneById(Long idPersonne);
		public List<Personne> readAllPersonne();

		
		//Gestion Des Clients
		public Client saveClient(Client c);
		public void mergeClient(Client c);
		public void removeClient(Long idClient);
		public Client readClientById(Long idClient);
		public List<Client> readClients();
		public List<Client> readClientsByKeyWord(String keyWord);

		
		//Gestion Des Employes
		public Employe saveEmploye(Employe e);
		public void mergeEmploye(Employe e);
		public Employe readEmployeById(Long idEmpl);
		public List<Employe> readAllEmployes();
		
		//Gestion Des Comptes
		public Compte saveAccount(Compte c,Long idClient, Long idEmpl);
		public void mergeAccount(Compte c);
		public void removeAccount(Long idCompte);
		public Compte readAccountById(Long idCompte);
		public List<Compte> readAccountsByClient(Long idClient);
		public List<Compte> readAccountsByEmpl(Long idEmpl);
		public List<Compte> readAllAccount();
		
		//Gestion Des Operations
		public Operation saveOperation(Operation op, Long idCompte , Long idEmpl);
		public void mergeOperation(Operation op);
		public void removeOperation(Long idOp);
		public Operation readOperationById(Long idOp);
		public List<Operation> readOperationsByClient(Long idClient);
		public List<Operation> readOperationsByAccount(Long idCompte);
		public List<Operation> readOperationsByEmployee(Long idEmpl);
		public List<Operation> readOperationsByFailed(Long idCompte );
		public List<Operation> readAllOperations();
		public void verser(double mt, Long idCompte, Long codeEmp);
		public void retirer(double mt, Long idCompte, Long codeEmp);
		public void virement(double mt, Long idCompte1, Long idCompte2,Long codeEmp);
		
		//Gestion Des Roles
		public Role saveRole(Role r);
		public void attributRole(String name, Long userID);
		public Role readRoleByRoleName(String roleName);
		public Role readRoleById(Long idRole);
		public void removeRole(Long idRole);
		public List<Role> readRolesByUser(Long idUser);
		public List<Role> readAllRoles();
		
		//Gestion des Users
		public User saveUser(User u);
		public User readUserById(Long idUser);
		public void removeUser(Long idUser);
		public User readUserByPersonne(Long idPersonne);
		public Personne readPersonneByUser(Long idUser);
		public User readUserByUserName(String name);
	
	
	

	
	

}
