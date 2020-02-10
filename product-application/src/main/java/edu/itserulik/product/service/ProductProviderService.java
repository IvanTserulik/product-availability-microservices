package edu.itserulik.product.service;

import edu.itserulik.product.client.item.CatalogItemClient;
import edu.itserulik.product.client.item.InventoryItemClient;
import edu.itserulik.product.model.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Objects;
import java.util.Set;

@Service
@Slf4j
public class ProductProviderService {

    private final InventoryItemClient inventoryItemClient;
    private final CatalogItemClient catalogItemClient;

    public ProductProviderService(CatalogItemClient catalogItemClient,
                                  InventoryItemClient inventoryItemClient) {
        this.catalogItemClient = catalogItemClient;
        this.inventoryItemClient = inventoryItemClient;
    }

    public Flux<Item> getItemByIds(Set<String> ids) {
        return buildItemFlux(catalogItemClient.getItemsByIds(ids),
                inventoryItemClient.getItemsByIds(ids));
    }

    public Flux<Item> getItemsBySku(String sku) {
        return buildItemFlux(catalogItemClient.getItemsBySku(sku),
                inventoryItemClient.getItemsBySku(sku));
    }

    private Flux<Item> buildItemFlux(Flux<Item> catalogItems,
                                     Flux<Item> inventoryItems) {
        var groupedCatalog = catalogItems.groupBy(Item::getUniqId);
        var groupedInventory = inventoryItems.groupBy(Item::getUniqId);

        return Flux.zip(groupedCatalog, groupedInventory)
                .filter(tupleOfGrouped -> Objects.equals(tupleOfGrouped.getT1().key(), tupleOfGrouped.getT2().key()))
                .flatMap(tupleOfGrouped -> Flux.zip(tupleOfGrouped.getT1(), tupleOfGrouped.getT2()))
                .map(tupleOfItems -> {
                    tupleOfItems.getT1()
                            .setAvailable(tupleOfItems.getT2().getAvailable());
                    return tupleOfItems.getT1();
                });
    }
}
