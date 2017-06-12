package com.cmrh.auth.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
	
	@RequestMapping({ "/getUserName" })
	public String user(Principal principal) {
		return principal.getName();
	}
	
	@RequestMapping({ "/logout" })
	public String logout() {
		return "注销成功";
	}
}
