import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var producer = new KafkaProducer<String, String>(properties());
        var value = "123,456,789";
        var email = "teste@teste.com.br";

        var record = new ProducerRecord<>("VIRTUAL_STORE_NEW_ORDER", value, value);
        var emailRecord = new ProducerRecord<>("VIRTUAL_STORE_SEND_EMAIL", email, email);

        producer.send(record, callback()).get();
        producer.send(emailRecord, callback()).get();
    }

    private static Callback callback() {
        return (data, error) -> {
            if (error != null) {
                error.printStackTrace();
                return;
            }

            System.out.println(
                    "Success:" + data.topic()
                            + ":::partition" + data.partition()
                            + "/ offset:" + data.offset()
                            + "/ timestamp:" + data.timestamp());
        };
    }

    public static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return properties;
    }
}
