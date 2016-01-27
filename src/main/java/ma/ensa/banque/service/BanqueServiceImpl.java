package ma.ensa.banque.service;

import java.util.List;

import ma.ensa.banque.dao.IBanqueDao;
import ma.ensa.banque.entities.Client;
import ma.ensa.banque.entities.Compte;
import ma.ensa.banque.entities.Employe;
import ma.ensa.banque.entities.Operation;
import ma.ensa.banque.entities.Personne;
import ma.ensa.banque.entities.Retrait;
import ma.ensa.banque.entities.Role;
import ma.ensa.banque.entities.User;
import ma.ensa.banque.entities.Versement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("banqueService")
@Transactional
public class BanqueServiceImpl implements IBanqueService {

	@Autowired
	public IBanqueDao dao;
//********************************************************************************************************************************************
// Gestions Des Operations
//********************************************************************************************************************************************
	@Override
	public void verser(double mt, Long idCompte, Long codeEmp) {
		Compte cp = dao.getAccountById(idCompte);
		dao.addOperation(new Versement(mt, true), idCompte, codeEmp);
		cp.setSolde(cp.getSolde() + mt);
	}

	@Override
	public void retirer(double mt, Long idCompte, Long codeEmp) {
		Compte cp = dao.getAccountById(idCompte);
		dao.addOperation(new Retrait(mt, true), idCompte, codeEmp);
		cp.setSolde(cp.getSolde() - mt);

	}

	@Override
	public void virement(double mt, Long idCompte1, Long idCompte2, Long codeEmp) {
		retirer(mt, idCompte1, codeEmp);
		verser(mt, idCompte2, codeEmp);

	}
//********************************************************************************************************************************************

	@Override
	public Personne readPersonneById(Long idPersonne) {
		// TODO Auto-generated method stub
		return dao.getPersonneById(idPersonne);
	}

	@Override
	public List<Personne> readAllPersonne() {
		// TODO Auto-generated method stub
		return dao.getAllPersonne();
	}

	@Override
	public Client saveClient(Client c) {
		// TODO Auto-generated method stub
		return dao.addClient(c);
	}

	@Override
	public void mergeClient(Client c) {
		// TODO Auto-generated method stub
		System.out.println(c.getTelePersonne() + "******************************************");
		dao.updateClient(c);
		System.out.println(c.getTelePersonne() + "***222222222222222222222222222222222222*******");
	}

	@Override
	public void removeClient(Long idClient) {
		// TODO Auto-generated method stub
		dao.deleteClient(idClient);
	}

	@Override
	public Client readClientById(Long idClient) {
		// TODO Auto-generated method stub
		return dao.getClientById(idClient);
	}

	@Override
	public List<Client> readClients() {
		// TODO Auto-generated method stub
		return dao.getClients();
	}

	@Override
	public List<Client> readClientsByKeyWord(String keyWord) {
		// TODO Auto-generated method stub
		return dao.consultClients(keyWord);
	}

	@Override
	public Employe saveEmploye(Employe e) {
		// TODO Auto-generated method stub
		return dao.addEmploye(e);
	}

	@Override
	public void mergeEmploye(Employe e) {
		// TODO Auto-generated method stub
		dao.updateEmploye(e);
	}

	@Override
	public Employe readEmployeById(Long idEmpl) {
		// TODO Auto-generated method stub
		return dao.getEmployeById(idEmpl);
	}

	@Override
	public List<Employe> readAllEmployes() {
		// TODO Auto-generated method stub
		return dao.getAllEmployes();
	}

	@Override
	public Compte saveAccount(Compte c, Long idClient, Long idEmpl) {
		// TODO Auto-generated method stub
		return dao.addAccount(c, idClient, idEmpl);
	}

	@Override
	public void mergeAccount(Compte c) {
		// TODO Auto-generated method stub
		dao.updateAccount(c);
	}

	@Override
	public void removeAccount(Long idCompte) {
		// TODO Auto-generated method stub
		dao.deleteAccount(idCompte);
	}

	@Override
	public Compte readAccountById(Long idCompte) {
		// TODO Auto-generated method stub
		return dao.getAccountById(idCompte);
	}

	@Override
	public List<Compte> readAccountsByClient(Long idClient) {
		// TODO Auto-generated method stub
		return dao.getAccountsByClient(idClient);
	}

	@Override
	public List<Compte> readAllAccount() {
		// TODO Auto-generated method stub
		return dao.getAllAccount();
	}

	@Override
	public Operation saveOperation(Operation op, Long idCompte, Long idEmpl) {
		// TODO Auto-generated method stub
		return dao.addOperation(op, idCompte, idEmpl);
	}

	@Override
	public void mergeOperation(Operation op) {
		// TODO Auto-generated method stub
		dao.updateOperation(op);
	}

	@Override
	public void removeOperation(Long idOp) {
		// TODO Auto-generated method stub
		dao.deleteOperation(idOp);
	}

	@Override
	public Operation readOperationById(Long idOp) {
		// TODO Auto-generated method stub
		return dao.getOperationById(idOp);
	}

	@Override
	public List<Operation> readOperationsByClient(Long idClient) {
		// TODO Auto-generated method stub
		return dao.getOperationsByClient(idClient);
	}

	@Override
	public List<Operation> readOperationsByAccount(Long idCompte) {
		// TODO Auto-generated method stub
		return dao.getOperationsByAccount(idCompte);
	}

	@Override
	public List<Operation> readOperationsByEmployee(Long idEmpl) {
		// TODO Auto-generated method stub
		return dao.getOperationsByEmployee(idEmpl);
	}

	@Override
	public List<Operation> readOperationsByFailed(Long idCompte) {
		// TODO Auto-generated method stub
		return dao.getOperationsByFailed(idCompte);
	}

	@Override
	public List<Operation> readAllOperations() {
		// TODO Auto-generated method stub
		return dao.getAllOperations();
	}

	@Override
	public Role saveRole(Role r) {
		// TODO Auto-generated method stub
		return dao.addRole(r);
	}

	@Override
	public void attributRole(String name, Long userID) {
		// TODO Auto-generated method stub
		dao.attributeRole(name, userID);
	}

	@Override
	public Role readRoleByRoleName(String roleName) {
		// TODO Auto-generated method stub
		return dao.getRoleByRoleName(roleName);
	}

	@Override
	public Role readRoleById(Long idRole) {
		// TODO Auto-generated method stub
		return dao.getRoleById(idRole);
	}

	@Override
	public void removeRole(Long idRole) {
		// TODO Auto-generated method stub
		dao.deleteRole(idRole);
	}

	@Override
	public List<Role> readRolesByUser(Long idUser) {
		// TODO Auto-generated method stub
		return dao.getRolesByUser(idUser);
	}

	@Override
	public List<Role> readAllRoles() {
		// TODO Auto-generated method stub
		return dao.getAllRoles();
	}

	@Override
	public User saveUser(User u) {
		// TODO Auto-generated method stub
		return dao.addUser(u);
	}

	@Override
	public User readUserById(Long idUser) {
		// TODO Auto-generated method stub
		return dao.getUserById(idUser);
	}

	@Override
	public void removeUser(Long idUser) {
		// TODO Auto-generated method stub
		dao.deleteUser(idUser);
	}

	@Override
	public User readUserByPersonne(Long idPersonne) {
		// TODO Auto-generated method stub
		return dao.getUserByPersonne(idPersonne);
	}

	@Override
	public Personne readPersonneByUser(Long idUser) {
		// TODO Auto-generated method stub
		return dao.getPersonneByUser(idUser);
	}

	@Override
	public User readUserByUserName(String name) {
		// TODO Auto-generated method stub
		return dao.getUserByUserName(name);
	}

	@Override
	public List<Compte> readAccountsByEmpl(Long idEmpl) {
		// TODO Auto-generated method stub
		return dao.getAccountsByEmpl(idEmpl);
	}

	@Override
	public List<Operation> readAllOperationsByFailed() {
		// TODO Auto-generated method stub
		return dao.getAllOperationsByFailed();
	}



}
