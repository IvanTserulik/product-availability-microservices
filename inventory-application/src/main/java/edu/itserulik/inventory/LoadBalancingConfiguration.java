package edu.itserulik.inventory;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancingConfiguration {

    @Bean
    public IRule balancingRule() {
        return new BestAvailableRule();
    }

}
