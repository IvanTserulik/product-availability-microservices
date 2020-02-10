package edu.itserulik.product.client.item;

import edu.itserulik.product.client.ItemClient;
import edu.itserulik.product.model.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Set;

@Component
@Slf4j
public class CatalogItemClient implements ItemClient {

    private final BaseItemClient cardClient;

    public CatalogItemClient(WebClient.Builder webClientBuilder,
                             @Value("${catalog-application.url}") String url) {
        this.cardClient = new BaseItemClient(webClientBuilder, url);
    }

    public Flux<Item> getItemsByIds(Set<String> ids) {
        return cardClient.getItemsByIds(ids)
                .flatMapMany(res -> res.bodyToFlux(Item.class));
    }

    public Flux<Item> getItemsBySku(String sku) {
        return cardClient.getItemsBySku(sku)
                .flatMapMany(res -> res.bodyToFlux(Item.class));
    }
}