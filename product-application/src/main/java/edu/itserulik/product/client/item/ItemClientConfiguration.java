package edu.itserulik.product.client.item;

import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemClientConfiguration {

    @Bean
    public HystrixCommandProperties.Setter getHystrixSetter(
            @Value("${hystrix.timeoutInMillisecond}") Integer timeoutInMillisecond,
            @Value("${hystrix.volumeThreshold}") Integer volumeThreshold,
            @Value("${hystrix.sleepWindowInMilliseconds}") Integer sleepWindowInMilliseconds
    ) {
        return HystrixCommandProperties.Setter()
                .withExecutionTimeoutInMilliseconds(1000)
                .withCircuitBreakerRequestVolumeThreshold(1)
                .withCircuitBreakerSleepWindowInMilliseconds(10000);
    }
}
