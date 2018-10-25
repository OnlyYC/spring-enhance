package com.liaoyb.limit.config;

import com.liaoyb.limit.core.CommonAspect;
import com.liaoyb.limit.core.Limit;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author liaoyb
 * @date 2018-10-25 15:00
 */
@Configuration
@Import(CommonAspect.class)
public class LimitAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean("limit")
    public Limit limit() {
        return new Limit();
    }
}
