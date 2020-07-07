package common.study.kafka.demo2;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Properties;

public class TestConsumerDemo {

    @Test
    public void test(){
        String topicName = "order-stat-push";

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "10.169.158.172:9092,10.169.158.207:9092");
        properties.put("group.id", "20200701-2");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
//        kafkaConsumer.subscribe(Arrays.asList(topicName));
        kafkaConsumer.assign( Arrays.asList( new TopicPartition(topicName, 0)));
        kafkaConsumer.seek(new TopicPartition(topicName, 0), 593854);
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, partition= %s, key = %s, value = %s", record.offset(), record.partition(), record.key(), record.value());
                System.out.println();
            }
        }
    }
}
