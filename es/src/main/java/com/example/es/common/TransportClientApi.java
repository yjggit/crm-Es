package com.example.es.common;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author jianhui.Yang
 * @version $Id TransportClientApi.java, v 0.1 2018-09-19 11:10 jianhui.Yang Exp $$
 */
public class TransportClientApi {

    //ES集群名称
    private static String INDEX = "threees";

    private static String ELK_INDEX = "elkLogCrmApi";

    /**
     * 三只眼ES
     * @return
     */
    public static TransportClient getTransportClient() {

        Settings settings = Settings.builder()
                .put("cluster.name",INDEX)
                .build();
        TransportClient client = null;

        try {
            client = new PreBuiltTransportClient(settings)
                    //setES集群地址，tcp访问端口为：9300；http方式访问端口则修改为9200
                    .addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName("192.168.1.193"),9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return client;
    }


    /**
     * ELK 索引客户端
     * @return
     */
    public static TransportClient getElkTransportClient() {

        Settings settings = Settings.builder()
                .put("cluster.name",ELK_INDEX)
                .build();
        TransportClient client = null;

        try {
            client = new PreBuiltTransportClient(settings)
                    //setES集群地址，tcp访问端口为：9300；http方式访问端口则修改为9200
                    .addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName("192.168.1.23"),9300));
        }catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return client;
    }


}
