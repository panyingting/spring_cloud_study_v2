package com.my.study.es;


import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EsQueryTest {

    @Autowired
    private TransportClient transportClient;



    @Test
    public void queryTest(){
        GetResponse response = transportClient.prepareGet("retailers-app-14-2019.08", "retailers-app", "uzd41WwBSKldwu6qpNRZ").get();
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void pageTest(){
        List<String> list = search();

        System.out.println(list);
    }


    private List<String> search(){

        int pageSize = 60;
        Random random = new Random(47);
        for(int i=0; i<100000; i++){

            int from = random.nextInt(i);
            long times = System.currentTimeMillis();
            SearchResponse response = transportClient.prepareSearch("retailers-app-14-2019.08")
                    .setTypes("retailers-app")
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.wildcardQuery("path", "*order*"))                 // Query
                .setQuery(QueryBuilders.wildcardQuery("message", "*LocalCacheRegistry*"))                 // Query
//                    .setPostFilter(QueryBuilders.m)     // Filter
                    .setFrom(i*pageSize).setSize(pageSize).setExplain(true)
                    .get();
//        SearchResponse response =client.prepareSearch().execute().actionGet();
            SearchHits searchHits = response.getHits();
            List<String> data = new ArrayList<String>();
            SearchHit[] hits = searchHits.getHits();
            for (SearchHit hit : hits) {
                String json = hit.getSourceAsString();
                data.add(json);
            }
            long times2 = System.currentTimeMillis();
            System.out.println(" 执行结果 i:"+i+"  时间："+(times2- times)/1000 +" 总数："+searchHits.getTotalHits());
        }

        return null;

    }

}
