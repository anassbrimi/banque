package ma.ensa.banque.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import ma.ensa.banque.entities.Personne;
import ma.ensa.banque.utilities.IApplicationManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ma.ensa.banque.entities.Client;
import ma.ensa.banque.entities.Compte;
import ma.ensa.banque.entities.Employe;
import ma.ensa.banque.entities.Operation;
import ma.ensa.banque.entities.Role;
import ma.ensa.banque.entities.User;

@Repository("banqueDao")
public class BanqueDaoImpl implements IBanqueDao {

	// @PersistenceContext
	// private EntityManager em;

	@Autowired
	public IApplicationManager managementUtil;
	@Autowired
	private SessionFactory sessionFactory;

	// ***********************************************************************************************************************************
	// Gestion Des Personnes
	// ***********************************************************************************************************************************

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Personne getPersonneById(Long idPersonne) {
		// TODO Auto-generated method stub
		return (Personne) getSession().get(Personne.class, idPersonne);
	}

	public List<Personne> getAllPersonne() {
		return getSession().createQuery("from Personne p").list();
	}

	// ***********************************************************************************************************************************
	// Gestion Des Clients
	// ***********************************************************************************************************************************
	@Override
	public Client addClient(Client c) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		User u = new User(c.getEmailPersonne(), encoder.encode(c
				.getTelePersonne()), true);
		getSession().save(u);
		attributeRole("ROLE_CLIENT", u.getIdUser());
		c.setUser(u);
		getSession().save(c);
		return c;
	}

	@Override
	public void updateClient(Client c) {
		// Cache cs = (Cache) getSession().getEntityManagerFactory().getCache();
		// cs.containsEntity(Client.class, )
		// getSession().getEntityManagerFactory().getCache().evictAll();

		getSession().update(c);
		// Client tmp = this.getClientById(c.getIdPersonne());
		// SystgetSession().out.println("01 " + tmp);
		// getSession().refresh(tmp);

		// Session s = (Session) getSession().getDelegate();
		// s = s.getSessionFactory().openSession();
		// if(s.isOpen()){
		// s.evict(c);
		// s.update(c);
		// }
		// SystgetSession().out.println("" + c);

	}

	@Override
	public void deleteClient(Long idClient) {
		Client c = getClientById(idClient);
		Collection<Compte> cpts = getAccountsByClient(idClient);
		User u = getUserByPersonne(idClient);
		for (Compte cp : cpts) {
			deleteAccount(cp.getIdCompte());
		}
		deleteUser(u.getIdUser());
		getSession().delete(c);

	}

	@Override
	public Client getClientById(Long idClient) {
		return (Client) getSession().get(Client.class, idClient);
	}

	@Override
	public List<Client> getClients() {
		return getSession().createQuery("from Client").list();
	}

	@Override
	public List<Client> consultClients(String keyWord) {
		Query req = (Query) getSession()
				.createQuery(
						"select c from Client c where c.nomPersonne like :x or c.prenomPersonne like :x  or c.emailPersonne like :x");
		req.setParameter("x", "%" + keyWord + "%");
		return req.list();
	}

	// ***********************************************************************************************************************************
	// Gestion Des Employes
	// ***********************************************************************************************************************************

	@Override
	public Employe addEmploye(Employe e) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		User u = new User(e.getEmailPersonne(), encoder.encode(e
				.getTelePersonne()), true);
		getSession().persist(u);
		attributeRole("ROLE_EMPL", u.getIdUser());
		e.setUser(u);
		getSession().persist(e);
		return e;
	}

	@Override
	public void updateEmploye(Employe e) {
		getSession().merge(e);
	}

	@Override
	public Employe getEmployeById(Long idEmpl) {
		return (Employe) getSession().get(Employe.class, idEmpl);
	}

	@Override
	public List<Employe> getAllEmployes() {
		return getSession().createQuery("from Employe").list();
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
		getSession().save(c);
		return c;
	}

	@Override
	public void updateAccount(Compte c) {
		getSession().update(c);
	}

	@Override
	public void deleteAccount(Long idCompte) {
		Compte c = getAccountById(idCompte);
		Collection<Operation> ops = getOperationsByAccount(idCompte);
		for (Operation op : ops) {
			deleteOperation(op.getIdOp());
		}
		getSession().delete(c);

	}

	@Override
	public Compte getAccountById(Long idCompte) {
		return (Compte) getSession().get(Compte.class, idCompte);
	}

	@Override
	public List<Compte> getAccountsByClient(Long idClient) {
		Query req = (Query) getSession().createQuery(
				"select c from Compte c where c.client.idPersonne = :x");
		req.setParameter("x", idClient);
		return req.list();
	}

	@Override
	public List<Compte> getAccountsByEmpl(Long idEmpl) {
		Query req = (Query) getSession().createQuery(
				"select c from Compte c where c.employe.idPersonne = :x order by c.idCompte desc");
		req.setParameter("x", idEmpl);
		return req.list();
	}

	@Override
	public List<Compte> getAllAccount() {
		Query req = (Query) getSession().createQuery("select c from Compte c order by c.idCompte desc");
		return req.list();
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
		getSession().persist(op);
		return op;
	}

	@Override
	public void updateOperation(Operation op) {
		getSession().merge(op);

	}

	@Override
	public void deleteOperation(Long idOp) {
		Operation op = getOperationById(idOp);
		getSession().delete(op);
	}

	@Override
	public Operation getOperationById(Long idOp) {
		return (Operation) getSession().get(Operation.class, idOp);
	}

	@Override
	public List<Operation> getOperationsByClient(Long idClient) {
//		List<Operation> operations = new ArrayList<Operation>();
//		List<Compte> comptes = getAccountsByClient(idClient);
//		for (Compte c : comptes) {
//			operations.addAll(getOperationsByAccount(c.getIdCompte()));
//		}
//	
//		return operations;
		Query req = getSession()
				.createQuery(
						"select o from Operation o where o.compte.client.idPersonne = :x ");
		req.setParameter("x", idClient);
		return req.list();
	}

	@Override
	public List<Operation> getOperationsByAccount(Long idCompte) {
		Query req = getSession()
				.createQuery(
						"select o from Operation o where o.compte.idCompte = :x order by o.idOp desc");
		req.setParameter("x", idCompte);
		return req.list();
	}

	@Override
	public List<Operation> getOperationsByEmployee(Long idEmpl) {
		Query req = getSession().createQuery(
				"select o from Operation o where o.employe.idPersonne = :x");
		req.setParameter("x", idEmpl);
		return req.list();
	}

	@Override
	public List<Operation> getAllOperations() {
		Query req = getSession().createQuery("select o from Operation o");
		return req.list();
	}

	@Override
	public List<Operation> getOperationsByFailed(Long idCompte) {
		Query req = getSession()
				.createQuery(
						"select o from Operation o where o.compte.idCompte = :x and o.statut=:y order by o.dateOperation");
		req.setParameter("x", idCompte);
		req.setParameter("y", false);
		return req.list();
	}

	// ***********************************************************************************************************************************
	// Gestion Des Roles
	// ***********************************************************************************************************************************

	@Override
	public List<Role> getAllRoles() {
		Query req = getSession().createQuery("select r from Role r ");
		return req.list();
	}

	@Override
	public Role addRole(Role r) {
		getSession().persist(r);
		return r;
	}

	public void attributeRole(String roleName, Long userID) {
		User u = getUserById(userID);
		Role r = new Role(roleName);
		u.getRoles().add(r);
		addRole(r);

	}

	@Override
	public void deleteRole(Long idRole) {
		Role r = getRoleById(idRole);
		getSession().delete(r);
	}

	@Override
	public List<Role> getRolesByUser(Long idUser) {
		Query req = getSession().createQuery(
				"select roles from User u where u.idUser = :x");
		req.setParameter("x", idUser);
		return req.list();
	}

	@Override
	public Role getRoleById(Long idRole) {
		return (Role) getSession().get(Role.class, idRole);
	}

	@Override
	public Role getRoleByRoleName(String roleName) {
		Query req = getSession().createQuery(
				"select r from Role r where r.roleName = :x");
		req.setParameter("x", roleName);
		return (Role) req.list().get(0);
	}

	// ***********************************************************************************************************************************
	// Gestion Des Roles
	// ***********************************************************************************************************************************

	@Override
	public User addUser(User u) {
		getSession().persist(u);
		return u;
	}

	@Override
	public User getUserById(Long idUser) {
		// TODO Auto-generated method stub
		return (User) getSession().get(User.class, idUser);
	}

	@Override
	public void deleteUser(Long idUser) {
		User u = getUserById(idUser);
		Collection<Role> roles = getRolesByUser(idUser);
		for (Role r : roles) {
			deleteRole(r.getIdRole());
		}
		getSession().delete(u);

	}

	@Override
	public User getUserByPersonne(Long idPersonne) {
		Query req = getSession().createQuery(
				"select user from Personne p where p.idPersonne = :x");
		req.setParameter("x", idPersonne);
		return (User) req.list().get(0);
	}

	@Override
	public Personne getPersonneByUser(Long idUser) {
		Query req = getSession().createQuery(
				"select p from Personne p where p.user.idUser = :x");
		req.setParameter("x", idUser);
		return (Personne) req.list().get(0);
	}

	@Override
	public User getUserByUserName(String name) {
		Query req = getSession().createQuery(
				"select u from User u where u.userName = :x");
		req.setParameter("x", name);
		return (User) req.list().get(0);
	}

	// ***********************************************************************************************************************************

}
