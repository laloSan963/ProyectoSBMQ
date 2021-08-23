package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.components.CounterResponse;
import com.example.demo.controller.CounterController;

@SpringBootTest
class ProySbmqApplicationTests {

	@Autowired
	CounterController controlador;
	
	@Test
	void getKeyHitsTest() throws Exception {
		CounterResponse resp = null;
		for(int i = 0; i < 5; i++) {
			resp = controlador.setCounter("test98");
		}
		
		assertThat(5L).isEqualByComparingTo(resp.getHits());
	}

	@Test
	void getListKeyHitsTest() throws Exception {
		
		for(int i = 0; i < 5; i++) {
			controlador.setCounter("test96");
		}
		controlador.setCounter("test97");
		List<CounterResponse> respList = controlador.getCounter();
		assertThat(2).isEqualByComparingTo(respList.size());
		assertThat(1L).isEqualByComparingTo(respList.get(1).getHits());
	}
}
