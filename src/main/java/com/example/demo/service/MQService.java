package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.example.demo.model.KeyHitsEntity;

public interface MQService {
	
	public static final String QUEUE_NAME_SAVE = "save";
	public static final String QUEUE_NAME_GET = "get";
	
	public void sendMessageSave(String key,String queue) throws IOException, TimeoutException, InterruptedException;
	public KeyHitsEntity consumeMessageSave() throws IOException, TimeoutException, InterruptedException;
	public void sendMessageGet(String key) throws IOException, TimeoutException, InterruptedException;
	public List<KeyHitsEntity> consumeMessageGet() throws IOException, TimeoutException, InterruptedException;
}
