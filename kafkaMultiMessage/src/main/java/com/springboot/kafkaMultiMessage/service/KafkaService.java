package com.springboot.kafkaMultiMessage.service;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class KafkaService {

    private final KafkaTemplate<String, String> stringKafkaTemplate;
    private final KafkaTemplate<String, Object> jsonKafkaTemplate;
    private final List<String> stringMessages = new ArrayList<>();
    private final List<Object> jsonMessages = new ArrayList<>();

    @Value("${kafka.topic.string}")
    private String stringTopic;

    @Value("${kafka.topic.json}")
    private String jsonTopic;

    public KafkaService(KafkaTemplate<String, String> stringKafkaTemplate,
                        KafkaTemplate<String, Object> jsonKafkaTemplate) {
        this.stringKafkaTemplate = stringKafkaTemplate;
        this.jsonKafkaTemplate = jsonKafkaTemplate;
    }

    // Producer methods
    public void sendStringMessage(String key, String message) {
        log.info("Publishing string message with key: {} to topic: {}", key, stringTopic);
        stringKafkaTemplate.send(stringTopic, key, message);
    }

    public void sendJsonMessage(String key, Object jsonData) {
        log.info("Publishing JSON message with key: {} to topic: {}", key, jsonTopic);
        jsonKafkaTemplate.send(jsonTopic, key, jsonData);
    }

    // Consumer methods
    @KafkaListener(topics = "${kafka.topic.string}",
            groupId = "string-consumer-group",
            containerFactory = "stringKafkaListenerContainerFactory")
    public void consumeStringMessage(ConsumerRecord<String, String> record) {
        String key = record.key();
        String message = record.value();
        log.info("Consumed string message with key: {} - Message: {}", key, message);
        stringMessages.add(message);
    }

    @KafkaListener(topics = "${kafka.topic.json}",
            groupId = "json-consumer-group",
            containerFactory = "jsonKafkaListenerContainerFactory")
    public void consumeJsonMessage(ConsumerRecord<String, Object> record) {
        String key = record.key();
        Object message = record.value();
        log.info("Consumed JSON message with key: {} - Message: {}", key, message);
        jsonMessages.add(message);
    }

    // Getter methods for retrieved messages
    public List<String> getStringMessages() {
        return new ArrayList<>(stringMessages);
    }

    public List<Object> getJsonMessages() {
        return new ArrayList<>(jsonMessages);
    }

    // Clear messages methods (optional)
    public void clearStringMessages() {
        stringMessages.clear();
    }

    public void clearJsonMessages() {
        jsonMessages.clear();
    }
}