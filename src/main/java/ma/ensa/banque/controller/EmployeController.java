package ma.ensa.banque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/employe")
public class EmployeController {
	
	
	@RequestMapping(value ="/home")
	public String login(Model model) {
		return "employe/home";
	}
	
	
	

}
