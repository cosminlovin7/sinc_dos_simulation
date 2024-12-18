package com.paj2ee.sinc_dos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloDashboardController {

	@GetMapping("/dashboard")
	public String getDashboard() {
		return "dashboard";
	}

}
