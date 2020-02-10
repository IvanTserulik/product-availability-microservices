package edu.itserulik.product.client.item;

import edu.itserulik.product.client.BaseClient;
import org.springframework.cloud.netflix.hystrix.HystrixCommands;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BaseItemClient extends BaseClient {

    public BaseItemClient(WebClient.Builder webClientBuilder,
                          String baseUrl) {
        super(webClientBuilder, baseUrl);
    }

    public Mono<ClientResponse> getItemsByIds(Set<String> ids) {
        return handleResponse(this.get("/getByIds", createQuery(ids), MediaType.APPLICATION_STREAM_JSON));
    }

    public Mono<ClientResponse> getItemsBySku(String sku) {
        return handleResponse(this.get("/getBySku", createQuery(sku), MediaType.APPLICATION_STREAM_JSON));
    }

    private Map<String, String> createQuery(Set<String> ids) {
        Map<String, String> query = new HashMap<>();
        query.put("ids", String.join(",", ids));
        return query;
    }

    private Map<String, String> createQuery(String sku) {
        Map<String, String> query = new HashMap<>();
        query.put("sku", sku);
        return query;
    }

    private <T> Mono<T> handleResponse(Mono<T> responseMono) {
        return HystrixCommands
                .from(responseMono)
                .commandProperties(setter -> setter.withExecutionTimeoutInMilliseconds(1000)
                        .withCircuitBreakerRequestVolumeThreshold(1)
                        .withCircuitBreakerSleepWindowInMilliseconds(10000))
                .commandName("getItems")
                .toMono();
    }
}
