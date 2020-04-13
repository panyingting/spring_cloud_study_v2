package common.study.kafka.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


//Create java class named “SimpleProducer"
public class SimpleProducer {

    private static final Logger logger = LoggerFactory.getLogger(SimpleProducer.class);

    public static void main(String[] args) throws Exception {


        //Assign topicName to string variable
        String topicName = "test_topic_28";

        // create instance for properties to access producer configs
        Properties props = new Properties();

        //Assign localhost id
        props.put("bootstrap.servers", "1000.200.100012:9092");

        //Set acknowledgements for producer requests.
        props.put("acks", "all");

        //If the request fails, the producer can automatically retry,
        props.put("retries", 5);

        //Specify buffer size in config
        props.put("batch.size", 16384);

        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);

        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("enable.idempotence","true");
        Producer<String, String> producer = new KafkaProducer<>(props);

        Thread.sleep(1000);
        for (int i = 100; i < 120; i++)
            producer.send( new ProducerRecord<>(topicName,  Integer.toString(i)), (metadata, exception) ->{
                System.out.println( String.format("执行完成，metadata:%s, exception:%s", metadata, exception));
            });

        System.out.println("Message sent successfully");
        producer.close();
    }



}
