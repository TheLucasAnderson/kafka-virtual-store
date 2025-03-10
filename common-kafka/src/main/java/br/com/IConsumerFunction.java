package br.com;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface IConsumerFunction<T> {
    void consume(ConsumerRecord<String, T> record);
}
