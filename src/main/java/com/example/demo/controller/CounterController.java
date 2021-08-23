package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.components.CounterResponse;
import com.example.demo.service.CounterService;

@RestController
@RequestMapping(value = "/hit", headers = "Accept=application/json")
public class CounterController {

	@Autowired
	CounterService counterService;
	
	@GetMapping("/{key:^[a-zA-Z0-9]*}")
	public CounterResponse setCounter(@PathVariable String key) throws Exception {
		System.out.println("entra al servicio key setCounter");
		return counterService.setCounter(key);
	}
	
	@GetMapping()
	public List<CounterResponse> getCounter() throws Exception {
		System.out.println("entra al servicio consume");
		return counterService.getCounter();
	}
}
