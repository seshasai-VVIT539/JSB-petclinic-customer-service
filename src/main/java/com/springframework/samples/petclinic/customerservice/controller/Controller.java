package com.springframework.samples.petclinic.customerservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class Controller {
	@GetMapping(value = "/")
	public RedirectView handleBaseUrl() {
		return new RedirectView("/swagger-ui/index.html");
	}
}
