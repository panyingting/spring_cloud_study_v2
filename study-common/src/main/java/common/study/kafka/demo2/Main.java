package common.study.kafka.demo2;


public class Main {
    public static void main(String[] args) {
        String url = "1000.200.100012:9092";
        String topic = "test_topic_02";

        //生产者
        KafkaProduce kp = new KafkaProduce(url);
        //生产一条消息
        kp.sendMessage(topic, "key", "{\"id\":\"1234312\",\"startDate\":\"20181009\",\"endDate\":\"20181009\",\"custname\":\"张飞\",\"custid\":\"100000001\"}");

    }
}
