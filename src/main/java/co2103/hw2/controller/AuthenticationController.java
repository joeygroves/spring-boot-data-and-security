package co2103.hw2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co2103.hw2.domain.Person;
import co2103.hw2.domain.UserKind;
import co2103.hw2.repository.PersonRepository;

@Controller
public class AuthenticationController {

	@Autowired
	PersonRepository personrepo;
	
	@RequestMapping(value = "/login-form", method = RequestMethod.GET)
	public String loginForm() {
		return "security/login";
	}

	@RequestMapping(value = "/error-login", method = RequestMethod.GET)
	public String invalidLogin(Model model) {
		model.addAttribute("error", true);
		return "security/login";
	}

	
	/**
	 * Successful login is a redirect based on the role of the user
	 * 
	 * @param principal
	 * @return
	 */
	/*
	@RequestMapping(value = "/success-login", method = RequestMethod.GET)
	public String successLogin(Principal principal) {
		Agent a = repo.findByName(principal.getName());
		if (a.isSpecial()) {
			return "redirect:/agents";
		}
		return "redirect:/reports";
	}
	*/
	
	@RequestMapping(value = "/success-login", method = RequestMethod.GET)
	public String successLogin(Principal principal) {
		Person p = personrepo.findByUsername((principal).getName());
		if (p.getKind().equals(UserKind.Manager)) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/b/bookings";
		}
		
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public String error() {
		return "security/denied";
	}
}
