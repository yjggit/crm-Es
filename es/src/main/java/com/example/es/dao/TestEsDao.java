package com.example.es.dao;
import com.alibaba.fastjson.JSONObject;
import com.example.es.common.TransportClientApi;
import com.example.es.vo.TestEsVO;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author jianhui.Yang
 * @version $Id TestEsDao.java, v 0.1 2018-09-19 11:25 jianhui.Yang Exp $$
 */
@Service
public class TestEsDao {

    private static String INDEX = "browsing_history";

    TransportClient clientApi = TransportClientApi.getTransportClient();

    public List<TestEsVO> findData(String distance, String orgName){
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        if (distance != null && !distance.equals("")) {
            qb.must(QueryBuilders.termQuery("distance",distance));
        }
        if (orgName != null && !orgName.equals("")) {
            qb.must(QueryBuilders.termQuery("orgName",orgName));
        }

        SearchRequestBuilder query = clientApi.prepareSearch(INDEX)
                .setQuery(qb)
                .setTypes("products")
                .setFrom(0)
                .setSize(5);

        SearchResponse response = query.get();
        ArrayList<TestEsVO> list = new ArrayList<>();
        Iterator<SearchHit> hits = response.getHits().iterator();
        while (hits.hasNext()){
            SearchHit hit = hits.next();
            TestEsVO vo = JSONObject.parseObject(hit.getSourceAsString(), TestEsVO.class );
            list.add(vo);
        }

        return list;
    }
}
