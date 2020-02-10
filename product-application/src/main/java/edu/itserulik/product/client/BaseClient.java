package edu.itserulik.product.client;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;

public class BaseClient {

    private final WebClient webClient;

    public BaseClient(WebClient.Builder webClientBuilder,
                      String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    protected Mono<ClientResponse> get(String uri, Map<String, String> variables,
                                       MediaType mediaType) {
        return webClient.get()
                .uri(uriBuilder -> buildQuery(uriBuilder, uri, variables))
                .accept(mediaType)
                .exchange();
    }

    private URI buildQuery(UriBuilder uriBuilder, String uri, Map<String, String> variables) {
        uriBuilder.replacePath(uri);
        variables.forEach(uriBuilder::queryParam);
        return uriBuilder.build();
    }
}
