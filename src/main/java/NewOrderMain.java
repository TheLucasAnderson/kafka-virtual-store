import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (var dispatcher = new KafkaDispatcher()) {
            for (var i = 0; i < 10; i++) {
                var key = UUID.randomUUID().toString();
                var value = "123,456,789";
                dispatcher.send("VIRTUAL_STORE_NEW_ORDER", key, value);

                var email = "teste@teste.com.br";
                dispatcher.send("VIRTUAL_STORE_SEND_EMAIL", key, email);
            }
        }
    }
}
