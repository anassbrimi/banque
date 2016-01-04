package ma.ensa.banque.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ma.ensa.banque.entities.Client;
import ma.ensa.banque.entities.Compte;
import ma.ensa.banque.entities.Employe;
import ma.ensa.banque.entities.Operation;
import ma.ensa.banque.entities.Personne;
import ma.ensa.banque.entities.Role;
import ma.ensa.banque.entities.User;
import ma.ensa.banque.utilities.IApplicationManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("banqueDao")
@Transactional
public class BanqueDaoImpl implements IBanqueDao {
	
	

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	public IApplicationManager managementUtil;

// ***********************************************************************************************************************************
// Gestion Des Personnes
// ***********************************************************************************************************************************
	
	@Override
	public Personne getPersonneById(Long idPersonne) {
		// TODO Auto-generated method stub
		return em.find(Personne.class, idPersonne);
	}
	
	public List<Personne> getAllPersonne(){
		Query req = em.createQuery("select p from Personne p");
		return req.getResultList();
		
	}
// ***********************************************************************************************************************************
// Gestion Des Clients
// ***********************************************************************************************************************************
	@Override
	public Client addClient(Client c) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		User u = new  User(c.getEmailPersonne(), encoder.encode(c.getTelePersonne()), true);
		em.persist(u);
		attributeRole("ROLE_CLIENT", u.getIdUser());		
		c.setUser(u);
		em.persist(c);
		return c;
	}
	
	@Override
	public void updateClient(Client c) {
		em.merge(c);
		System.out.println("" + c);

	}


	@Override
	public void deleteClient(Long idClient) {
		 Client c = getClientById(idClient);
		 Collection<Compte> cpts = getAccountsByClient(idClient);
		 User u = getUserByPersonne(idClient);
		 for(Compte cp : cpts){
			 deleteAccount(cp.getIdCompte());
		 }
		 deleteUser(u.getIdUser());
		 em.remove(c);
		
	}


	@Override
	public Client getClientById(Long idClient) {
		return em.find(Client.class, idClient);
	}


	@Override
	public List<Client> getClients() {
		Query req = em.createQuery("select c from Client c");
		return req.getResultList();
	}


	@Override
	public List<Client> consultClients(String keyWord) {
		Query req = em
				.createQuery("select c from Client c where c.nomPersonne like :x or c.prenomPersonne like :x  or c.emailPersonne like :x");
		req.setParameter("x", "%" + keyWord + "%");
		return req.getResultList();
	}
	
	
// ***********************************************************************************************************************************
// Gestion Des Employes
// ***********************************************************************************************************************************

	
	@Override
	public Employe addEmploye(Employe e) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		User u = new  User(e.getEmailPersonne(), encoder.encode(e.getTelePersonne()), true);
		em.persist(u);
		attributeRole("ROLE_EMPL", u.getIdUser());		
		e.setUser(u);
		em.persist(e);
		return e;
	}
	
	@Override
	public void updateEmploye(Employe e) {
		em.merge(e);
	}


	@Override
	public Employe getEmployeById(Long idEmpl) {
		return em.find(Employe.class, idEmpl);
	}


	@Override
	public List<Employe> getAllEmployes() {
		Query req = em.createQuery("select e from Employe e");
		return req.getResultList();
	}
	
// ***********************************************************************************************************************************
// Gestion Des Comptes
// ***********************************************************************************************************************************

	
	@Override
	public Compte addAccount(Compte c, Long idClient, Long idEmpl) {
		Client client = getClientById(idClient);
		Employe empl = getEmployeById(idEmpl);
		String formatedDate = managementUtil.dateCaster(new Date());
		c.setDateCreation(formatedDate);
		c.setClient(client);
		c.setEmploye(empl);
		em.persist(c);
		return c;
	}


	@Override
	public void updateAccount(Compte c) {
		em.merge(c);
	}


	@Override
	public void deleteAccount(Long idCompte) {
		Compte c = getAccountById(idCompte);
		Collection<Operation> ops = getOperationsByAccount(idCompte);
		for(Operation op : ops){
			deleteOperation(op.getIdOp());
		}
		em.remove(c);
		
	}


	@Override
	public Compte getAccountById(Long idCompte) {
		return em.find(Compte.class, idCompte);
	}


	@Override
	public List<Compte> getAccountsByClient(Long idClient) {
		Query req = em
				.createQuery("select c from Compte c where c.client.idPersonne = :x");
		req.setParameter("x", idClient);
		return req.getResultList();
	}


	@Override
	public List<Compte> getAllAccount() {
		Query req = em.createQuery("select c from Compte c");
		return req.getResultList();
	}
	
	
	
	
// ***********************************************************************************************************************************
// Gestion Des Operations
// ***********************************************************************************************************************************

	
	@Override
	public Operation addOperation(Operation op, Long idCompte, Long idEmpl) {
		Compte cp = getAccountById(idCompte);
		Employe emp = getEmployeById(idEmpl);
		String formatedDate = managementUtil.dateCaster(new Date());
		op.setDateOperation(formatedDate);
		op.setCompte(cp);
		op.setEmploye(emp);
		em.persist(op);
		return op;
	}


	@Override
	public void updateOperation(Operation op) {
		em.merge(op);
		
	}


	@Override
	public void deleteOperation(Long idOp) {
		Operation op = getOperationById(idOp);
		em.remove(op);
	}


	@Override
	public Operation getOperationById(Long idOp) {
		return em.find(Operation.class, idOp);
	}


	@Override
	public List<Operation> getOperationsByClient(Long idClient) {
		List<Operation> operations = new ArrayList<Operation>();
		List<Compte> comptes = getAccountsByClient(idClient);
		for(Compte c : comptes){
			operations.addAll(getOperationsByAccount(c.getIdCompte()));
		}
		return operations;
	}


	@Override
	public List<Operation> getOperationsByAccount(Long idCompte) {
		Query req = em
				.createQuery("select o from Operation o where o.compte.idCompte = :x order by o.dateOperation");
		req.setParameter("x", idCompte);
		return req.getResultList();
	}


	@Override
	public List<Operation> getOperationsByEmployee(Long idEmpl) {
		Query req = em
				.createQuery("select o from Operation o where o.employe.idPersonne = :x");
		req.setParameter("x", idEmpl);
		return req.getResultList();
	}


	@Override
	public List<Operation> getAllOperations() {
		Query req = em.createQuery("select o from Operation o");
		return req.getResultList();
	}
	
	
	@Override
	public List<Operation> getOperationsByFailed(Long idCompte) {
		Query req = em
				.createQuery("select o from Operation o where o.compte.idCompte = :x and o.statut=:y order by o.dateOperation");
		req.setParameter("x", idCompte);
		req.setParameter("y", false);
		return req.getResultList();
	}

	
// ***********************************************************************************************************************************
// Gestion Des Roles
// ***********************************************************************************************************************************

	
	
	@Override
	public List<Role> getAllRoles() {
		Query req = em
				.createQuery("select r from Role r ");
		return req.getResultList();
	}
	
	@Override
	public Role addRole(Role r) {
		em.persist(r);
		return r;
	}
	
	
	
	public void attributeRole(String roleName, Long userID)  {
		User u = getUserById(userID);
		Role r = new Role(roleName);
		u.getRoles().add(r);
		addRole(r);
		
	}


	@Override
	public void deleteRole(Long idRole) {
		Role r = getRoleById(idRole);
		em.remove(r);
	}


	@Override
	public List<Role> getRolesByUser(Long idUser) {
		Query req = em
				.createQuery("select roles from User u where u.idUser = :x");
		req.setParameter("x", idUser);
		return req.getResultList();
	}
	@Override
	public Role getRoleById(Long idRole) {
		return em.find(Role.class, idRole);
	}

	@Override
	public Role getRoleByRoleName(String roleName) {
		Query req = em
				.createQuery("select r from Role r where r.roleName = :x");
		req.setParameter("x", roleName);
		return (Role) req.getResultList().get(0);
	}
	
	
// ***********************************************************************************************************************************
// Gestion Des Roles
// ***********************************************************************************************************************************


	@Override
	public User addUser(User u) {
		em.persist(u);		
		return u;
	}

	@Override
	public User getUserById(Long idUser) {
		// TODO Auto-generated method stub
		return em.find(User.class, idUser);
	}

	@Override
	public void deleteUser(Long idUser) {
		User u = getUserById(idUser);
		Collection<Role> roles = getRolesByUser(idUser);
		for(Role r: roles){
			deleteRole(r.getIdRole());
		}
		em.remove(u);
		
	}

	@Override
	public User getUserByPersonne(Long idPersonne) {
		Query req = em
				.createQuery("select user from Personne p where p.idPersonne = :x");
		req.setParameter("x", idPersonne);
		return (User) req.getResultList().get(0);
	}

	@Override
	public Personne getPersonneByUser(Long idUser) {
		Query req = em
				.createQuery("select p from Personne p where p.user.idUser = :x");
		req.setParameter("x", idUser);
		return (Personne) req.getResultList().get(0);
	}

	@Override
	public User getUserByUserName(String name) {
		Query req = em
				.createQuery("select u from User u where u.userName = :x");
		req.setParameter("x", name);
		return  (User) req.getResultList().get(0);
	}


	


	
// ***********************************************************************************************************************************

	

	



	

}
