package common.study.kafka.topic;

import kafka.admin.AdminUtils;
import kafka.server.ConfigType;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;
import scala.collection.JavaConversions;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Query {


    /**
     * 获取所有的TopicList
     *
     * @return
     */
    public static void getTopicList() {
        ZkUtils zkUtils = ZkUtils.apply("1000.200.100012:2179,1000.200.100012:2180,1000.200.100012:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        List<String> allTopicList = JavaConversions.seqAsJavaList(zkUtils.getAllTopics());
        System.out.println(allTopicList.size());
        for (String topic : allTopicList) {
            System.out.println("topic name:" + topic);
        }
        zkUtils.close();

    }

    public static void main(String[] args) {
        ZkUtils zkUtils = ZkUtils.apply("1000.200.100012:2179,1000.200.100012:2180,1000.200.100012:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());
// 获取topic 'test'的topic属性属性
        Properties props = AdminUtils.fetchEntityConfig(zkUtils, ConfigType.Topic(), "test");
// 查询topic-level属性
        Iterator it = props.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + " = " + value);
        }
        getTopicList();
        zkUtils.close();
    }
}
