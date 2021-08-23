package com.example.demo.service;

import java.util.List;

import com.example.demo.components.CounterResponse;

public interface CounterService {
	public CounterResponse setCounter(String key) throws Exception;
	public List<CounterResponse> getCounter() throws Exception;
}
