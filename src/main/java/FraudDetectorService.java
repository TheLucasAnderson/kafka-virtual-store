import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class FraudDetectorService {
    public static void main(String[] args) {
        var consumer = new KafkaConsumer<String, String>(properties());
        consumer.subscribe(Collections.singletonList("VIRTUAL_STORE_NEW_ORDER"));

        while (true) {
            var records = consumer.poll(Duration.ofMillis(100));

            if(!records.isEmpty()) {
                System.out.println("Records found: " + records.count());

                for(var record : records) {
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
        }
    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, FraudDetectorService.class.getSimpleName());

        return properties;
    }
}
