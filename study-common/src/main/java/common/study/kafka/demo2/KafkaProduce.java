package common.study.kafka.demo2;

import kafka.utils.ZkUtils;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class KafkaProduce {
    private static Properties properties;

    KafkaProduce(String url) {
        properties = new Properties();
        properties.put("bootstrap.servers", url);
        properties.put("producer.type", "sync");
        properties.put("request.required.acks", "1");
        properties.put("serializer.class", "kafka.serializer.DefaultEncoder");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("bak.partitioner.class", "kafka.producer.DefaultPartitioner");
        properties.put("bak.key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("bak.value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }


    /**
     * @Title: sendMessage
     * @Description: 生产一个消息
     */
    public void sendMessage(String topic, String key, String value) {
        // 实例化produce
        KafkaProducer<String, String> kp = new KafkaProducer<String, String>(properties);

        // 消息封装
        ProducerRecord<String, String> pr = new ProducerRecord<String, String>(topic, key, value);

        // 发送数据
        kp.send(pr, new Callback() {
            // 回调函数
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (null != exception) {
                    System.out.println("记录的offset在:" + metadata.offset());
                    System.out.println(exception.getMessage() + exception);
                }
            }
        });

        // 关闭produce
        kp.close();
    }


    public static void main(String[] args) {

        ZkUtils zkUtils;
        String url = "10.10.10.44:9092";
        String topic = "partition_topic_01";

        //生产者
        KafkaProduce kp = new KafkaProduce(url);
        //生产一条消息

        for (int i = 0; i < 2000; i++) {
            System.out.println("key:" + i);
            kp.sendMessage(topic, String.valueOf(i), "{\"id\":\"1234312\",\"startDate\":\"20181009\",\"endDate\":\"20181009\",\"custname\":\"张飞\",\"custid\":\"100000001\"}");
        }

    }
}
