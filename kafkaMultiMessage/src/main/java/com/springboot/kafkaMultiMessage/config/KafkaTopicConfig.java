package com.springboot.kafkaMultiMessage.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.string}")
    private String stringTopic;

    @Value("${kafka.topic.json}")
    private String jsonTopic;

    @Value("${kafka.topic.partitions:3}")
    private int partitions;

    @Value("${kafka.topic.replication-factor:1}")
    private short replicationFactor;

//    @Bean
//    public KafkaAdmin kafkaAdmin() {
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "${kafka.bootstrap-servers}");
//        return new KafkaAdmin(configs);
//    }

    @Bean
    public NewTopic stringTopic() {
        return TopicBuilder.name(stringTopic)
                .partitions(partitions)
                .replicas(replicationFactor)
                .compact()
                .build();
    }

    @Bean
    public NewTopic jsonTopic() {
        return TopicBuilder.name(jsonTopic)
                .partitions(partitions)
                .replicas(replicationFactor)
                .compact()
                .build();
    }

//    // Optional: Create a topic with custom configurations
//    @Bean
//    public NewTopic deadLetterTopic() {
//        Map<String, String> configs = new HashMap<>();
//        configs.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
//        configs.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); // 1 day retention
//        configs.put(TopicConfig.SEGMENT_MS_CONFIG, "3600000"); // 1 hour segment
//
//        return TopicBuilder.name("dead-letter-topic")
//                .partitions(1)
//                .replicas(replicationFactor)
//                .configs(configs)
//                .build();
//    }
}