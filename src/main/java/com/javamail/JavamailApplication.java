package com.javamail;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class JavamailApplication {
	private static final String TOPIC = "user-email-preferences";
	private static final String BOOTSTRAP_SERVERS = "localhost:9092";
	private static final String GROUP_ID = "email-service-group";
	private static final JavaMailService mailService = new JavaMailService();
	public static void main(String[] args) {


		Properties props= new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); // Read as raw JSON string
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");


		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(List.of("account-creation-email","otp-email"));

		// 2. Create a Thread Pool for Asynchronous Email Sending
		ExecutorService emailThreadPool = Executors.newFixedThreadPool(3);

		System.out.println("Kafka Email Consumer started, polling for messages...");
		try{
			while(true)
			{
				ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(100));
				for(ConsumerRecord<String,String> record: records )
				{
					emailThreadPool.submit(() ->{
						if(record.key().equals(record.value()))
						mailService.sendAccountCreationMail(record.key());
						else {

							DTO dto = new DTO();
							dto.setEmail(record.key());
							dto.setOtp(record.value());
							mailService.sendMail(dto);
						}
					});
				}
			}
		}
		catch (Exception e)
		{}

	}

}
