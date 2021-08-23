package com.example.demo.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.components.CounterResponse;
import com.example.demo.model.KeyHitsEntity;
import com.example.demo.service.CounterService;
import com.example.demo.service.MQService;

@Service
public class CounterServiceImpl implements CounterService{
	
	@Autowired
	MQService mqService;
	
	@Override
	public CounterResponse setCounter(String key) throws Exception {
		try {
			mqService.sendMessageSave(key,MQService.QUEUE_NAME_SAVE);
			KeyHitsEntity keyHitsEntity = mqService.consumeMessageSave();
			return new CounterResponse.Builder().key(keyHitsEntity.getKey()).hits(keyHitsEntity.getHits()).build();
		}catch(Exception e) {
			System.out.println("error al enviar mensaje");
			e.printStackTrace();
		}
		throw new Exception();		
	}

	@Override
	public List<CounterResponse> getCounter() throws Exception {
		
		try {
			mqService.sendMessageSave(MQService.QUEUE_NAME_GET,MQService.QUEUE_NAME_GET);
			List<CounterResponse> counterResponseList = new LinkedList<>();
			mqService.consumeMessageGet().forEach(keyHitsEntity -> counterResponseList.add(
					new CounterResponse.Builder().key(keyHitsEntity.getKey()).hits(keyHitsEntity.getHits()).build() 
					));
			return counterResponseList;
		}catch(Exception e) {
			System.out.println("error al consumir mensaje");
			e.printStackTrace();
		}
		
		throw new Exception();
	}

}
