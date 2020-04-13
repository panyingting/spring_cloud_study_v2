package common.study.kafka.topic;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;

import java.util.Properties;

public class KafkaUtil {

    public static void createKafaTopic(String ZkStr, KafkaTopicBean topic) {
        ZkUtils zkUtils = ZkUtils.
                apply(ZkStr, 30000, 30000, JaasUtils.isZkSecurityEnabled());

        AdminUtils.createTopic(zkUtils, topic.getTopicName(), topic.getPartition(),
                topic.getReplication(), new Properties(), new RackAwareMode.Enforced$());
        zkUtils.close();
    }

    public static void deleteKafaTopic(String ZkStr, KafkaTopicBean topic) {
        ZkUtils zkUtils = ZkUtils.
                apply(ZkStr, 30000, 30000, JaasUtils.isZkSecurityEnabled());

        AdminUtils.deleteTopic(zkUtils, topic.getTopicName());
        zkUtils.close();
    }


    public static void main(String[] args) {

        //zookeeper地址：端口号
        String ZkStr = "1000.200.100012:2179,1000.200.100012:2180,1000.200.100012:2181";

        //topic对象
        KafkaTopicBean topic = new KafkaTopicBean();
        topic.setTopicName("partition_topic_01");  //topic名称
        topic.setPartition(3);            //分区数量设置为1
        topic.setReplication(1);         //副本数量设置为1

        //创建topic
        KafkaUtil.createKafaTopic(ZkStr, topic);
        //删除topic
        //KafkaUtil.deleteKafaTopic(ZkStr,topic);

    }


}
