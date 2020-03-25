package com.chuck.norris.joke.config;

import com.chuck.norris.joke.apiutils.APIRateLimitInterceptor;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Component
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        Refill refill = Refill.greedy(5, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(5, refill).withInitialTokens(1);
        Bucket bucket = Bucket4j.builder().addLimit(limit).build();
        registry.addInterceptor(new APIRateLimitInterceptor(bucket, 1)).addPathPatterns("/last");

        refill = Refill.intervally(3, Duration.ofMinutes(1));
        limit = Bandwidth.classic(3, refill);
        bucket = Bucket4j.builder().addLimit(limit).build();
        registry.addInterceptor(new APIRateLimitInterceptor(bucket, 1))
                .addPathPatterns("/chuck/*");
    }

}
