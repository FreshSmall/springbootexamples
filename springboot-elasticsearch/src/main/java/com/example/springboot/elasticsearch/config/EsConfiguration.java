package com.example.springboot.elasticsearch.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class EsConfiguration {

    // 集群地址，多个用，隔开
    private static String hosts = "192.168.0.8";
    // 使用端口号
    private static int port = 9200;
    // 使用协议
    private static String schema = "http";

    private static ArrayList<HttpHost> hostList = null;

    // 连接超时时间
    private static final int connectTimeOut = 100;
    // 连接超时时间
    private static final int socketTimeOut = 100;
    // 获取链接的超时时间
    private static final int connectionRequestTimeOut = 100;

    // 最大连接数
    private static final int maxConnectNum = 5;
    // 最大路由连接数
    private static final int maxConnectPerRoute = 2;

    static {
        hostList = new ArrayList<>();
        String[] hostStrs = hosts.split(",");
        for (String host : hostStrs) {
            hostList.add(new HttpHost(host, port, schema));
        }
    }

    @Bean
    public RestHighLevelClient client() {
        RestClientBuilder builder = RestClient.builder(hostList.toArray(new HttpHost[0]));
        // 异步httpClient连接延时配置
        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder builder) {
                builder.setConnectTimeout(connectTimeOut);
                builder.setSocketTimeout(socketTimeOut);
                builder.setConnectionRequestTimeout(connectionRequestTimeOut);
                return builder;
            }
        });
        // 异步httpclient 连接数配置
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                httpAsyncClientBuilder.setMaxConnTotal(maxConnectNum);
                httpAsyncClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                return httpAsyncClientBuilder;
            }
        });
        return new RestHighLevelClient(builder);
    }

}
