import org.apache.kafka.clients.consumer.ConsumerRecord;

public class EmailService {
    public static void main(String[] args) {
        var emailService = new EmailService();
        var service = new KafkaService(EmailService.class.getSimpleName(), "VIRTUAL_STORE_SEND_EMAIL", emailService::parse);
        service.run();
    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("_____________________________");

        System.out.println("Sending email...");
        System.out.println("Key: " + record.key());
        System.out.println("Value: " + record.value());
        System.out.println("Partition: " + record.partition());
        System.out.println("Offset: " + record.offset());
        System.out.println("Email Sent!");

        System.out.println("_____________________________");
    }
}
