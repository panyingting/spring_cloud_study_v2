package common.study.kafka.demo2;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;


public class KafkaConsume {
    private static Properties properties;
    private long SIZE = 100;
    KafkaConsumer<String, String> consumer;

    KafkaConsume(String url) {
        properties = new Properties();
        properties.put("bootstrap.servers", url);
        properties.put("zookeeper.connect", "1000.200.100012:2179,1000.200.100012:2180,1000.200.100012:2181");
        properties.put("group.id", "group-1");
        properties.put("zookeeper.session.timeout.ms", "4000");
        properties.put("zookeeper.sync.time.ms", "200");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    }


    /**
     * @Title: getMessage
     * @Description: 消费一个消息
     */
    public void getMessage(String topic) throws InterruptedException {

        System.out.println(
                "Consumer。。。。。。。 begin"
        );
        consumer = new KafkaConsumer<String, String>(properties);
        // 订阅主题
        consumer.subscribe(Collections.singletonList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(SIZE);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
                System.out.println();
            }
            if (records.count() > 0) {
                System.out.printf("count:%d,\n", records.count());
                Thread.sleep(2000L);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = "1000.200.100012:9092";
        String topic = "partition_topic_01";
        //消费者
        KafkaConsume kc = new KafkaConsume(url);
        kc.getMessage(topic);
    }
}