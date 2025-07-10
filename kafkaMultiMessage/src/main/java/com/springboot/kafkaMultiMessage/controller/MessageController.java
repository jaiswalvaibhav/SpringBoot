package com.springboot.kafkaMultiMessage.controller;

import com.springboot.kafkaMultiMessage.service.KafkaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/kafka")
@AllArgsConstructor
public class MessageController {

    private final KafkaService kafkaService;

//    public MessageController(KafkaService kafkaService) {
//        this.kafkaService = kafkaService;
//    }

    @PostMapping("/publish/string")
    public ResponseEntity<String> publishString(@RequestBody Map<String, String> request) {
        try {
            String key = request.get("key");
            String message = request.get("message");
            kafkaService.sendStringMessage(key, message);
            return ResponseEntity.ok("String message published successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error publishing string message: " + e.getMessage());
        }
    }

    @PostMapping("/publish/json")
    public ResponseEntity<String> publishJson(@RequestBody Map<String, Object> request) {
        try {
            String key = (String) request.get("key");
            Object jsonData = request.get("data");
            kafkaService.sendJsonMessage(key, jsonData);
            return ResponseEntity.ok("JSON message published successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error publishing JSON message: " + e.getMessage());
        }
    }

    @GetMapping("/messages/string")
    public ResponseEntity<List<String>> getStringMessages() {
        return ResponseEntity.ok(kafkaService.getStringMessages());
    }

    @GetMapping("/messages/json")
    public ResponseEntity<List<Object>> getJsonMessages() {
        return ResponseEntity.ok(kafkaService.getJsonMessages());
    }
}