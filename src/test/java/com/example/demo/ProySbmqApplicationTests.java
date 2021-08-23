package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.demo.components.CounterResponse;
import com.example.demo.controller.CounterController;

@SpringBootTest
class ProySbmqApplicationTests {

	@Autowired
	CounterController controlador;
	
	@Test
	void getKeyHitsTest() throws Exception {
		ResponseEntity<CounterResponse> resp = null;
		for(int i = 0; i < 5; i++) {
			resp = controlador.setCounter("application/json","test98");
		}
		
		assertThat(5L).isEqualByComparingTo(resp.getBody().getHits());
	}

	@Test
	void getListKeyHitsTest() throws Exception {
		
		for(int i = 0; i < 5; i++) {
			controlador.setCounter("application/json","test96");
		}
		controlador.setCounter("application/json","test97");
		ResponseEntity<List<CounterResponse>> respList = controlador.getCounter("application/json");
		assertThat(2).isEqualByComparingTo(respList.getBody().size());
		assertThat(1L).isEqualByComparingTo(respList.getBody().get(1).getHits());
	}
}
