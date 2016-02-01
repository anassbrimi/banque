package ma.ensa.banque.controller;

import java.util.List;

import javax.validation.Valid;

import ma.ensa.banque.entities.Client;
import ma.ensa.banque.entities.Compte;
import ma.ensa.banque.entities.Employe;
import ma.ensa.banque.entities.Operation;
import ma.ensa.banque.model.BanqueForm;
import ma.ensa.banque.service.IBanqueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employe")
public class EmployeController {

	@Autowired
	private IBanqueService service;
	

	@RequestMapping(value = "/home")
	public String homePage(Model model) {
		User userS = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = userS.getUsername();
		ma.ensa.banque.entities.User user = service.readUserByUserName(name);
		Employe emp = service.readEmployeById(service
				.readPersonneByUser(user.getIdUser()).getIdPersonne());
		List<Compte> cpts = service.readAccountsByEmpl(emp.getIdPersonne());
		List<Operation> operations = service.readOperationsByEmployee(emp
				.getIdPersonne());
		model.addAttribute("ComptesOuver", cpts);
		model.addAttribute("OpsEff", operations.subList(0, 5));
		model.addAttribute("nombreCompteOuvr", cpts.size());
		model.addAttribute("nombreOpsEff", operations.size());
		return "employe/home";
	}

	@RequestMapping(value = "/clients")
	public String clientsPage(Model model) {
		List<Client> clts = service.readClients();
		model.addAttribute("client", new Client());
		model.addAttribute("clients", clts);
		return "employe/clients";
	}

	@RequestMapping(value = "/searchClient", method = RequestMethod.POST)
	public String searchClient(Model model, @RequestParam String keywords) {
		List<Client> clts = service.readClientsByKeyWord(keywords);
		model.addAttribute("client", new Client());
		model.addAttribute("clients", clts);
		return "employe/clients";
	}

	@RequestMapping(value = "/consultClient")
	public String consultClient(@Valid Long idCli, Model model, BanqueForm bf) {
		this.chargerCpte(bf, idCli);
		model.addAttribute("banqueForm", this.chargerCpte(bf, idCli));
		return "employe/details";
	}

	@RequestMapping(value = "/saveOperation")
	public String saveOperation(Model model, BanqueForm bf) {
		User userS = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = userS.getUsername();
		ma.ensa.banque.entities.User user = service.readUserByUserName(name);
		Employe emp = service.readEmployeById(service
				.readPersonneByUser(user.getIdUser()).getIdPersonne());

		if (bf.getTypeOperation().equals("VER")) {
			System.out.println(bf.getMontant());
			service.verser(bf.getMontant(), bf.getCompte().getIdCompte(),
					emp.getIdPersonne());

		} else if (bf.getTypeOperation().equals("RET")) {
			service.retirer(bf.getMontant(), bf.getCompte().getIdCompte(),
					emp.getIdPersonne());
		} else {

			service.virement(bf.getMontant(), bf.getCompte().getIdCompte(),
					bf.getCode2(), emp.getIdPersonne());
		}

		this.chargerCpte(bf, bf.getClient().getIdPersonne());
		return "employe/details";
	}
	
	@RequestMapping(value = "/addClient")
	public String addClient(Model model) {
		BanqueForm bf1 = new BanqueForm();
		bf1.setClient(new Client());
		bf1.setCompte(new Compte());
		model.addAttribute("banqueForm", bf1);
		return "employe/addClient";
	}
	
	@RequestMapping(value = "/saveClient")
	public String saveClient(Model model, BanqueForm bf) {
		User userS = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = userS.getUsername();
		ma.ensa.banque.entities.User user = service.readUserByUserName(name);
		Employe emp = service.readEmployeById(service
				.readPersonneByUser(user.getIdUser()).getIdPersonne());
		Client c = bf.getClient();
		service.saveClient(c);
		service.saveAccount(bf.getCompte(), c.getIdPersonne(), emp.getIdPersonne());
		
		return "employe/addClient";
	}
	
	
	@RequestMapping(value = "/accounts")
	public String getAccounts(Model model) {
		User userS = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = userS.getUsername();
		ma.ensa.banque.entities.User user = service.readUserByUserName(name);
		Employe emp = service.readEmployeById(service
				.readPersonneByUser(user.getIdUser()).getIdPersonne());
		List<Compte> cpts = service.readAccountsByEmpl(emp.getIdPersonne());
		List<Compte> cpts1 = service.readAllAccount();
		model.addAttribute("OpenedAccounts", cpts);
		model.addAttribute("OtherAccounts", cpts1);
		
		return "employe/accounts";
	}
	
	@RequestMapping(value = "/transactions")
	public String gettransactions(Model model) {
		User userS = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = userS.getUsername();
		ma.ensa.banque.entities.User user = service.readUserByUserName(name);
		Employe emp = service.readEmployeById(service
				.readPersonneByUser(user.getIdUser()).getIdPersonne());

		
		List<Operation> opts = service.readOperationsByEmployee(emp.getIdPersonne());
		List<Operation> opts1 = service.readAllOperations();
		model.addAttribute("transactionsDone", opts);
		model.addAttribute("allTransactions", opts1);
		
		return "employe/transactions";
	}
	
	
	
	
	

	public BanqueForm chargerCpte(BanqueForm bf, Long idCli) {
		try {
			
			Client c = service.readClientById(idCli);
			System.out.println(c.getNomPersonne()+"*********************************************");
			Compte cpt = service.readAccountsByClient(idCli).get(0);
			List<Operation> ops = service.readOperationsByAccount(cpt.getIdCompte());
			bf.setClient(c);
			bf.setCompte(cpt);
			bf.setOperations(ops);
		} catch (Exception e) {
			bf.setException(e.getMessage());
		}
		return bf;
	}

}
