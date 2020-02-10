package edu.itserulik.inventory.service;

import edu.itserulik.inventory.csv.CsvReader;
import edu.itserulik.inventory.model.Item;
import edu.itserulik.inventory.model.ItemAvailability;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ItemAvailabilityService {

    private final CsvReader csvReader;
    private final String fileName;

    public ItemAvailabilityService(CsvReader csvReader,
                                   @Value("${fileName}") String fileName) {
        this.csvReader = csvReader;
        this.fileName = fileName;
    }

    public Flux<ItemAvailability> getItemAvailabilityByIds(Set<String> ids) {
        return csvReader.getObjectsFromCsv(fileName, Item.class)
                .filter(item -> ids.contains(item.getUniqId()))
                .map(this::getAvailabilityForItem);
    }

    public Flux<ItemAvailability> getItemAvailabilityBySku(String sku) {
        return csvReader.getObjectsFromCsv(fileName, Item.class)
                .filter(item -> sku.equals(item.getSku()))
                .map(this::getAvailabilityForItem);
    }

    private ItemAvailability getAvailabilityForItem(Item item) {
        return ItemAvailability.builder()
                .uniqId(item.getUniqId())
                .available(ThreadLocalRandom.current().nextBoolean())
                .build();
    }
}
