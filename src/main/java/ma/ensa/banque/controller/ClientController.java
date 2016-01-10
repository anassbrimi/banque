package ma.ensa.banque.controller;

import java.util.ArrayList;
import java.util.List;

import ma.ensa.banque.entities.Client;
import ma.ensa.banque.entities.Compte;
import ma.ensa.banque.entities.Operation;
import ma.ensa.banque.entities.Retrait;
import ma.ensa.banque.entities.Versement;
import ma.ensa.banque.service.IBanqueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		ma.ensa.banque.entities.User user = service
				.readUserByUserName(name);
		Client c = service.readClientById(service.readPersonneByUser(
				user.getIdUser()).getIdPersonne());
		Compte cpt = service.readAccountsByClient(c.getIdPersonne()).get(
				0);
		List<Operation> ops = service.readOperationsByAccount(
				cpt.getIdCompte()).subList(0, 5);
		List<Operation> failedOps = service.readOperationsByFailed(cpt
				.getIdCompte());
		model.addAttribute("solde", cpt.getSolde());
		model.addAttribute("operations", ops);
		model.addAttribute("cashRetracted", failedOps.size());
		return "client/home";
	}

	@RequestMapping(value = "/transaction")
	public String trasactionClient(Model model) {
		User userS = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = userS.getUsername();
		ma.ensa.banque.entities.User user = service
				.readUserByUserName(name);
		Client c = service.readClientById(service.readPersonneByUser(
				user.getIdUser()).getIdPersonne());
		Compte cpt = service.readAccountsByClient(c.getIdPersonne()).get(
				0);
		List<Operation> ops = service.readOperationsByAccount(cpt
				.getIdCompte());
		List<Retrait> retraits = new ArrayList<Retrait>();
		List<Versement> versements = new ArrayList<Versement>();
		for (Operation op : ops) {
			if (op.toString() == "Versement") {
				versements.add((Versement) op);
			} else if (op.toString() == "Retrait") {
				retraits.add((Retrait) op);
			}
		}
		model.addAttribute("versements", versements);
		model.addAttribute("retraits", retraits);

		return "client/transaction";
	}

	@RequestMapping(value = "/cashRetracted")
	public String cashRPage(Model model) {
		User userS = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = userS.getUsername();
		ma.ensa.banque.entities.User user = service
				.readUserByUserName(name);
		Client c = service.readClientById(service.readPersonneByUser(
				user.getIdUser()).getIdPersonne());
		Compte cpt = service.readAccountsByClient(c.getIdPersonne()).get(
				0);
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
		ma.ensa.banque.entities.User user = service
				.readUserByUserName(name);
		Client c = service.readClientById(service.readPersonneByUser(
				user.getIdUser()).getIdPersonne());
		c.setUser(user);
		model.addAttribute("client", c);
		return "client/settings";
	}

	@RequestMapping(value = "/editClient")
	public String updateSettings(Model model, Client client) {
		if (client.getIdPersonne() != null) {
			
			service.mergeClient(client);
		}

		return "client/settings";
	}

}