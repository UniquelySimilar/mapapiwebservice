package com.tcoveney.mapapiwebservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mapapi")
public class MapApiController {
	@GetMapping("/test")
	public String test() {
		return "Hello from 'mapapiwebservice";
	}

}
