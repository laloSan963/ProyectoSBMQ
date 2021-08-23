package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.components.CounterResponse;
import com.example.demo.service.CounterService;

@RestController
@RequestMapping(value = "/hit", headers = "Accept=application/json")
@Validated
public class CounterController {
	
	private static final String HEADER_ACCEPT_APPLICATION_JSON = "application/json";
	
	@Autowired
	CounterService counterService;
	
	@GetMapping("/{key:^[a-zA-Z0-9]*}")
	public ResponseEntity<CounterResponse> setCounter(@RequestHeader("Accept") String value, @PathVariable String key) throws Exception {
		System.out.println("entra al servicio key setCounter -->"+value+"-->"+HEADER_ACCEPT_APPLICATION_JSON);
		if(!HEADER_ACCEPT_APPLICATION_JSON.equals(value)) {
			return ResponseEntity.badRequest().body(null);
		}
		return ResponseEntity.ok(counterService.setCounter(key));
	}
	
	@GetMapping()
	public ResponseEntity<List<CounterResponse>> getCounter(@RequestHeader("Accept") String value) throws Exception {
		System.out.println("entra al servicio consume");
		if(!HEADER_ACCEPT_APPLICATION_JSON.equals(value)) {
			return ResponseEntity.badRequest().body(null);
		}
		return ResponseEntity.ok(counterService.getCounter());
	}
}
