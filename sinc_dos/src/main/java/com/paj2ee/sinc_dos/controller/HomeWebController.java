package com.paj2ee.sinc_dos.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeWebController {

	@GetMapping("/home")
	public String getHome(Model model) {

		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();

//		if (
//			null == authentication
//			|| authentication instanceof AnonymousAuthenticationToken
//		) {
//			return "redirect:login";
//		}

		UserDetails userDetails = null;
		if (
			null == authentication
			|| authentication instanceof AnonymousAuthenticationToken
		) {
			//noop
		} else {
			userDetails = (UserDetails) authentication.getPrincipal();
		}

		String usernameAttribute = "unknown";
		if (null != userDetails) {
			usernameAttribute = userDetails.getUsername();
		} else {

		}

		model.addAttribute("username", usernameAttribute);

		return "home";

	}

}
