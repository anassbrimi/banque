package ma.ensa.banque.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ma.ensa.banque.batch.Filecontrole;
import ma.ensa.banque.entities.Client;
import ma.ensa.banque.entities.Compte;
import ma.ensa.banque.entities.Operation;
import ma.ensa.banque.entities.Retrait;
import ma.ensa.banque.entities.Versement;
import ma.ensa.banque.model.BanqueForm;
import ma.ensa.banque.service.IBanqueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	public IBanqueService service;

	@RequestMapping(value = "/home")
	public String homeClient(Model model) {
		User userS = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = userS.getUsername();
		ma.ensa.banque.entities.User user = service.readUserByUserName(name);
		Client c = service.readClientById(service.readPersonneByUser(
				user.getIdUser()).getIdPersonne());

		Compte cpt = service.readAccountsByClient(c.getIdPersonne()).get(0);
		List<Operation> ops = service
				.readOperationsByAccount(cpt.getIdCompte()).subList(0, 5);
		List<Operation> failedOps = service.readOperationsByFailed(cpt
				.getIdCompte());
		model.addAttribute("solde", cpt.getSolde());
		model.addAttribute("operations", ops);
		model.addAttribute("cashRetracted", failedOps.size());
		return "client/home";
	}

	@RequestMapping(value = "/transaction")
	public String trasactionClient(Model model, BanqueForm bf) {
		User userS = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = userS.getUsername();
		ma.ensa.banque.entities.User user = service.readUserByUserName(name);
		Client c = service.readClientById(service.readPersonneByUser(
				user.getIdUser()).getIdPersonne());
		chargerCpte(bf, c.getIdPersonne());
		List<Retrait> retraits = new ArrayList<Retrait>();
		List<Versement> versements = new ArrayList<Versement>();
		for (Operation op : bf.getOperations()) {
			if (op.toString() == "CREDIT" && op.isStatut() == true) {
				versements.add((Versement) op);
			} else if (op.toString() == "DEBIT" && op.isStatut() == true) {
				retraits.add((Retrait) op);
			}
		}
		model.addAttribute("banqueForm", bf);
		model.addAttribute("versements", versements);
		model.addAttribute("retraits", retraits);

		return "client/transaction";
	}

	@RequestMapping(value = "/cashRetracted")
	public String cashRPage(Model model) {
		User userS = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = userS.getUsername();
		ma.ensa.banque.entities.User user = service.readUserByUserName(name);
		Client c = service.readClientById(service.readPersonneByUser(
				user.getIdUser()).getIdPersonne());
		Compte cpt = service.readAccountsByClient(c.getIdPersonne()).get(0);
		List<Operation> failedOps = service.readOperationsByFailed(cpt
				.getIdCompte());
		model.addAttribute("failedOps", failedOps);
		return "client/cashRetracted";
	}

	@RequestMapping(value = "/settings")
	public String settingsPage(Model model) {
		User userS = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = userS.getUsername();
		ma.ensa.banque.entities.User user = service.readUserByUserName(name);
		Client c = service.readClientById(service.readPersonneByUser(
				user.getIdUser()).getIdPersonne());
		c.setUser(user);
		model.addAttribute("client", c);
		return "client/settings";
	}

	@RequestMapping(value = "/editClient")
	public String updateSettings(Model model,
			@ModelAttribute("client") Client client) {
		User userS = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = userS.getUsername();
		ma.ensa.banque.entities.User user = service.readUserByUserName(name);
		if (client.getIdPersonne() != null) {
			System.out.println(client.getTelePersonne());
			client.setUser(user);
			service.mergeClient(client);
		}

		return "client/settings";
	}

	@RequestMapping(value = "/saveOperation")
	public String saveOp(Model model, BanqueForm bf) {

		if (bf.getTypeOperation().equals("VER")) {

			service.verser(bf.getMontant(), bf.getCompte().getIdCompte(),
					service.readAccountById(bf.getCompte().getIdCompte())
							.getEmploye().getIdPersonne());
		} else if (bf.getTypeOperation().equals("RET")) {
			Random rand = new Random();
			int nombre = rand.nextInt(2);
			if (nombre == 0) {

				Operation op = new Operation();
				op = service.saveOperation(
						new Retrait(bf.getMontant(), false), bf.getCompte()
								.getIdCompte(),
						service.readAccountById(bf.getCompte().getIdCompte())
								.getEmploye().getIdPersonne());
				

				if (!op.isStatut()) {
					Filecontrole fc = new Filecontrole();
					fc.addInFile(op);
					System.out
							.println("*****************************************"
									+ op.isStatut());
				}
			} else {
				service.retirer(bf.getMontant(), bf.getCompte().getIdCompte(),
						service.readAccountById(bf.getCompte().getIdCompte())
								.getEmploye().getIdPersonne());
			}
		} else {

			service.virement(bf.getMontant(), bf.getCompte().getIdCompte(),
					bf.getCode2(),
					service.readAccountById(bf.getCompte().getIdCompte())
							.getEmploye().getIdPersonne());

		}

		return "redirect:transaction";
	}

	public BanqueForm chargerCpte(BanqueForm bf, Long idCli) {
		try {

			Client c = service.readClientById(idCli);
			Compte cpt = service.readAccountsByClient(idCli).get(0);
			List<Operation> ops = service.readOperationsByAccount(cpt
					.getIdCompte());
			bf.setClient(c);
			bf.setCompte(cpt);
			bf.setOperations(ops);
		} catch (Exception e) {
			bf.setException(e.getMessage());
		}
		return bf;
	}

}