package edu.itserulik.product.controller;

import edu.itserulik.product.model.Item;
import edu.itserulik.product.service.ProductProviderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Set;

@RestController
public class ProductController {
    private final ProductProviderService productProviderService;

    public ProductController(ProductProviderService productProviderService) {
        this.productProviderService = productProviderService;
    }

    @GetMapping(path = "/getBySku", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Item> getItemsBySku(@RequestParam("sku") String sku) {
        return productProviderService.getItemsBySku(sku);
    }

    @GetMapping(path = "/getByIds", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Item> getItemByIds(@RequestParam("ids") Set<String> ids) {
        return productProviderService.getItemByIds(ids);
    }
}
