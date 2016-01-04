package ma.ensa.banque.dao;

import java.util.List;

import ma.ensa.banque.entities.Client;
import ma.ensa.banque.entities.Compte;
import ma.ensa.banque.entities.Employe;
import ma.ensa.banque.entities.Operation;
import ma.ensa.banque.entities.Personne;
import ma.ensa.banque.entities.Role;
import ma.ensa.banque.entities.User;

public interface IBanqueDao {
	
	//Gestion Des Personnes
	public Personne getPersonneById(Long idPersonne);
	public List<Personne> getAllPersonne();

	
	//Gestion Des Clients
	public Client addClient(Client c);
	public void updateClient(Client c);
	public void deleteClient(Long idClient);
	public Client getClientById(Long idClient);
	public List<Client> getClients();
	public List<Client> consultClients(String keyWord);

	
	//Gestion Des Employes
	public Employe addEmploye(Employe e);
	public void updateEmploye(Employe e);
	public Employe getEmployeById(Long idEmpl);
	public List<Employe> getAllEmployes();
	
	//Gestion Des Comptes
	public Compte addAccount(Compte c,Long idClient, Long idEmpl);
	public void updateAccount(Compte c);
	public void deleteAccount(Long idCompte);
	public Compte getAccountById(Long idCompte);
	public List<Compte> getAccountsByClient(Long idClient);
	public List<Compte> getAllAccount();
	
	//Gestion Des Operations
	public Operation addOperation(Operation op, Long idCompte , Long idEmpl);
	public void updateOperation(Operation op);
	public void deleteOperation(Long idOp);
	public Operation getOperationById(Long idOp);
	public List<Operation> getOperationsByClient(Long idClient);
	public List<Operation> getOperationsByAccount(Long idCompte);
	public List<Operation> getOperationsByEmployee(Long idEmpl);
	public List<Operation> getOperationsByFailed(Long idCompte );
	public List<Operation> getAllOperations();
	
	//Gestion Des Roles
	public Role addRole(Role r);
	public void attributeRole(String name, Long userID);
	public Role getRoleByRoleName(String roleName);
	public Role getRoleById(Long idRole);
	public void deleteRole(Long idRole);
	public List<Role> getRolesByUser(Long idUser);
	public List<Role> getAllRoles();
	
	//Gestion des Users
	public User addUser(User u);
	public User getUserById(Long idUser);
	public void deleteUser(Long idUser);
	public User getUserByPersonne(Long idPersonne);
	public Personne getPersonneByUser(Long idUser);
	public User getUserByUserName(String name);

}
