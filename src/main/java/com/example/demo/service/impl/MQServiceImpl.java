package com.example.demo.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.KeyHitsEntity;
import com.example.demo.repository.KeyHitsRepository;
import com.example.demo.service.MQService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

@Service
public class MQServiceImpl implements MQService {

	@Autowired
	private KeyHitsRepository keyHitsRepository;

	@Override
	public void sendMessageSave(String key,String queue) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.queueDeclare(queue, false, false, false, null);

			channel.basicPublish("", queue, null, key.getBytes());

		} finally {
			channel.close();
			connection.close();
		}
	}

	@Override
	public KeyHitsEntity consumeMessageSave() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		try {
			channel.queueDeclare(QUEUE_NAME_SAVE, false, false, false, null);
			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					System.out.println(String.format("Received  «%s»", message));
				}
			};

			String mesa = new String(channel.basicGet(QUEUE_NAME_SAVE, true).getBody(), "UTF-8");
			channel.basicConsume(QUEUE_NAME_SAVE, true, consumer);

			KeyHitsEntity keyHitsEntity = keyHitsRepository.findById(mesa.trim()).orElse(getNewKeyHits(mesa.trim()));

			keyHitsEntity.setHits(keyHitsEntity.getHits() + 1L);
			keyHitsEntity = keyHitsRepository.save(keyHitsEntity);

			return keyHitsEntity;

		} finally {
			channel.close();
			connection.close();
		}
	}

	@Override
	public void sendMessageGet(String key) throws IOException, TimeoutException, InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<KeyHitsEntity> consumeMessageGet() throws IOException, TimeoutException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		try {
			channel.queueDeclare(QUEUE_NAME_GET, false, false, false, null);
			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					System.out.println(String.format("Received  «%s»", message));
				}
			};

			System.out.println(new String(channel.basicGet(QUEUE_NAME_GET, true).getBody(), "UTF-8"));
			channel.basicConsume(QUEUE_NAME_GET, true, consumer);

			return keyHitsRepository.findAll();

		} finally {
			channel.close();
			connection.close();
		}
	}

	private KeyHitsEntity getNewKeyHits(String key) {
		KeyHitsEntity keyHitsEntity = new KeyHitsEntity();
		keyHitsEntity.setHits(0L);
		keyHitsEntity.setKey(key);
		return keyHitsEntity;
	}

}
