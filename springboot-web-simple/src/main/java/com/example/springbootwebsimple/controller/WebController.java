package com.example.springbootwebsimple.controller;

import com.example.springbootwebsimple.dto.StudentDto;
import com.jayway.jsonpath.JsonPath;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.logbook.BodyFilters;
import org.zalando.logbook.ChunkingSink;
import org.zalando.logbook.CurlHttpLogFormatter;
import org.zalando.logbook.DefaultHttpLogWriter;
import org.zalando.logbook.DefaultSink;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.RequestFilters;
import org.zalando.logbook.ResponseFilters;
import org.zalando.logbook.json.JsonHttpLogFormatter;

import java.util.HashSet;
import java.util.Set;

import static java.util.regex.Pattern.compile;
import static org.zalando.logbook.BodyFilters.replaceFormUrlEncodedProperty;
import static org.zalando.logbook.Conditions.contentType;
import static org.zalando.logbook.HeaderFilters.authorization;
import static org.zalando.logbook.HeaderFilters.eachHeader;
import static org.zalando.logbook.QueryFilters.accessToken;
import static org.zalando.logbook.QueryFilters.replaceQuery;
import static org.zalando.logbook.json.JsonPathBodyFilters.jsonPath;

/**
 * @author: yinchao
 * @ClassName: WebController
 * @Description:
 * @team wuhan operational dev.
 * @date: 2023/8/8 21:11
 */
@RestController
public class WebController {

    /**
     * logbook 特性：
     * 1、详细打印http request 和response 相关的特性
     * 2、可以处理日志脱敏
     * 3、可以打印不同格式日志（http、json、curl）
     * 4、日志分块
     * 5、request 和 response 串联
     * 6、日志打印过滤
     * 7、不支持grpc接口相关日志操作
     *
     * @return
     */
    @Bean
    public Logbook logbook() {
        DefaultSink defaultSink = new DefaultSink(new CurlHttpLogFormatter(), new DefaultHttpLogWriter());
        return Logbook.builder()
                .sink(new ChunkingSink(defaultSink, 10))
                .requestFilter(RequestFilters.replaceBody(message -> contentType("audio/*").test(message) ? "mmh mmh mmh mmh" : null))
                .responseFilter(ResponseFilters.replaceBody(message -> contentType("*/*-stream").test(message) ? "It just keeps going and going..." : null))
                .queryFilter(accessToken())
                .queryFilter(replaceQuery("name", "<secret>"))
                .bodyFilter(jsonPath("$.name").replace("****"))
                .headerFilter(authorization())
                .build();
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "hello world!" + name;
    }


    @PostMapping("/student")
    public StudentDto testLog(@RequestBody StudentDto studentDto) {
        return studentDto;
    }
}
