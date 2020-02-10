package edu.itserulik.product.client;

import edu.itserulik.product.model.Item;
import reactor.core.publisher.Flux;

import java.util.Set;

public interface ItemClient {
    Flux<Item> getItemsByIds(Set<String> ids);
}
