package com.example.springboot.elasticsearch.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Field;

/**
 * @author: yinchao
 * @ClassName: ElasticsearchConfigChecker
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/1/26 22:43
 */
@Service
public class ElasticsearchConfigChecker {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @PostConstruct
    public void checkConnectionPoolConfig() {
        try {
            // 获取 RestClient 实例
            Object restClient = getFieldValue(restHighLevelClient, "client");
            // 获取 HttpClient 实例
            CloseableHttpClient httpClient = (CloseableHttpClient) getFieldValue(restClient, "client");

            // 获取连接管理器
            PoolingHttpClientConnectionManager connectionManager = (PoolingHttpClientConnectionManager)
                    getFieldValue(httpClient, "connManager");

            // 打印连接池配置
            System.out.println("Max Total Connections: " + connectionManager.getMaxTotal());
            System.out.println("Default Max Per Route: " + connectionManager.getDefaultMaxPerRoute());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object getFieldValue(Object target, String fieldName) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(target);
    }
}
