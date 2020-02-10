package edu.itserulik.catalog.service;

import edu.itserulik.catalog.csv.CsvReader;
import edu.itserulik.catalog.model.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Set;

@Service
public class ItemProviderService {

    private final CsvReader csvReader;
    private final String fileName;

    public ItemProviderService(CsvReader csvReader,
                               @Value("${fileName}") String fileName) {
        this.csvReader = csvReader;
        this.fileName = fileName;
    }

    public Flux<Item> getItemsBySku(String sku) {
        return csvReader.getObjectsFromCsv(fileName, Item.class)
                .filter(item -> sku.equals(item.getSku()));
    }

    public Flux<Item> getItemByIds(Set<String> ids) {
        return csvReader.getObjectsFromCsv(fileName, Item.class)
                .filter(item -> ids.contains(item.getUniqId()));
    }
}
