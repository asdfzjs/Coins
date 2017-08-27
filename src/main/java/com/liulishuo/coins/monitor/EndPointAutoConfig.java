package com.liulishuo.coins.monitor;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jisongzhu on 2017/8/27.
 */
@Configuration
public class EndPointAutoConfig {
    @Bean
    public Endpoint<String> customEndPoint() {
        return new JstackEndPoint();
    }
}

