package com.cmrh.auth.app.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	@RequestMapping("/")
	public String home(Principal user) {
		return "Hello " + user.getName();
	}
}
