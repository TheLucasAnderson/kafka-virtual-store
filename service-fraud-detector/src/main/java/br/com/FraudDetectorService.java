package br.com;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class FraudDetectorService {

    public static void main(String[] args) {
        var fraudDetectorService = new FraudDetectorService();
        try (var service = new KafkaService(Order.class, "VIRTUAL_STORE_NEW_ORDER", fraudDetectorService::parse, Map.of());) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, Order> record) {
        System.out.println("_____________________________");

        System.out.println("Processing new order and checking for fraud...");
        System.out.println("Key: " + record.key());
        System.out.println("Value: " + record.value());
        System.out.println("Partition: " + record.partition());
        System.out.println("Offset: " + record.offset());
        System.out.println("New order processed!");

        System.out.println("_____________________________");
    }
}
