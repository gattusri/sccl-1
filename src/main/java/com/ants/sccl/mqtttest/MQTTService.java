package com.ants.sccl.mqtttest;

import static com.hivemq.client.mqtt.MqttGlobalPublishFilter.ALL;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;


@Service
public class MQTTService {
	@Autowired
	MQTTRepository mqttRepository;
	
	final String host = "48eb834e5bfc48fdb7a8c21995b506eb.s1.eu.hivemq.cloud";
    final String username = "ants_mqtt";
    final String password = "Ants@123";
    	  Boolean flag=false;
    	  int i=0;
	
	public void mqtt(MQTTModel mc) {
	
    //create an MQTT client
    final Mqtt5BlockingClient client = MqttClient.builder()
            .useMqttVersion5()
            .serverHost(host)
            .serverPort(8883)
            .sslWithDefaultConfig()
            .buildBlocking();

    //connect to HiveMQ Cloud with TLS and username/pw
    client.connectWith()
            .simpleAuth()
            .username(username)
            .password(UTF_8.encode(password))
            .applySimpleAuth()
            .send();

    System.out.println("Connected successfully");

    //subscribe to the topic "my/test/topic"
    client.subscribeWith()
           // .topicFilter("welcome/temp")
            .topicFilter(mc.getDeviceTopic())
            .send();
    System.out.println("subscribe successfully");
    
  
    //set a callback that is called when a message is received (using the async API style)
    client.toAsync().publishes(ALL, publish -> {
    
    	//	i++;
    	
        System.out.println("Received message: " + publish.getTopic() + " -> " + UTF_8.decode(publish.getPayload().get()));

        //disconnect the client after a message was received
        //client.disconnect();
       // if(flag)
          
    });
//    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");    
//    LocalDateTime now = LocalDateTime.now();  
//    mc.setDate(formatter.format(now));
//    System.out.println(new java.sql.Date(endDate.getTime());
	 mqttRepository.save(mc);
	/*
	 * if(i>10) { client.disconnect(); flag=true; }
	 */
	   
   
    client.publishWith()
    .topic(mc.getDeviceTopic())
    .payload(UTF_8.encode(mc.getMessage()))
    .send();
    System.out.println("publish successfully");
	}
}
