package com.hms.ownerms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OwnerController {

		
	@GetMapping("/ownerhello")
	public String getHello(){
		return "Hello World1";
	}
	
}
