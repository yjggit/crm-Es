package com.example.es.dao;
import com.alibaba.fastjson.JSONObject;
import com.example.es.common.TransportClientApi;
import com.example.es.vo.ElkTestVO;
import com.example.es.vo.TestEsVO;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author jianhui.Yang
 * @version $Id TestEsDao.java, v 0.1 2018-09-19 11:25 jianhui.Yang Exp $$
 */
@Service
public class TestEsDao {

    private static Logger logger = LoggerFactory.getLogger(TestEsDao.class);

    private static String INDEX = "browsing_history";

    private static String ELK_INDEX = "logcrmapi";

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    TransportClient clientApi = TransportClientApi.getTransportClient();

    TransportClient client = TransportClientApi.getElkTransportClient();

    public List<TestEsVO> findData(String distance, String orgName){
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        if (distance != null && !distance.equals("")) {
            qb.must(QueryBuilders.termQuery("distance",distance));
        }
        if (orgName != null && !orgName.equals("")) {
            qb.must(QueryBuilders.termQuery("org_name",orgName));
        }

        SearchRequestBuilder query = clientApi.prepareSearch(INDEX)
                .setQuery(qb)
                .setTypes("products")
                .setFrom(0)
                .setSize(5);

        SearchResponse response = query.get();
        logger.info("\n"+query);
        ArrayList<TestEsVO> list = new ArrayList<>();
        Iterator<SearchHit> hits = response.getHits().iterator();
        while (hits.hasNext()){
            SearchHit hit = hits.next();
            TestEsVO vo = JSONObject.parseObject(hit.getSourceAsString(), TestEsVO.class );
            list.add(vo);
        }

        return list;
    }



    public List<ElkTestVO> findMessage (String message) {
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("message");

        if (message != null && !message.equals("")) {
            qb.must(QueryBuilders.matchQuery("message",message));
        }

        SearchRequestBuilder query = client.prepareSearch(ELK_INDEX)
                .setQuery(qb)
                .setTypes("doc")
                .addSort("@timestamp", SortOrder.DESC)
                .highlighter(highlightBuilder)
                .setFrom(0)
                .setSize(5);
        SearchResponse response = query.get();
        logger.info("\n"+query);
        ArrayList<ElkTestVO> list = new ArrayList();
        Iterator<SearchHit> hits = response.getHits().iterator();
        while (hits.hasNext()){
            SearchHit hit = hits.next();

            ElkTestVO vo = JSONObject.parseObject(hit.getSourceAsString(),ElkTestVO.class);
            list.add(vo);
        }
        return list;
    }
}
