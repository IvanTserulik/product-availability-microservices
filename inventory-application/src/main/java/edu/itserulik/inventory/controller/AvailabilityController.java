package edu.itserulik.inventory.controller;

import edu.itserulik.inventory.model.ItemAvailability;
import edu.itserulik.inventory.service.ItemAvailabilityService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Set;

@RestController
public class AvailabilityController {
    private final ItemAvailabilityService itemAvailabilityService;

    public AvailabilityController(ItemAvailabilityService itemAvailabilityService) {
        this.itemAvailabilityService = itemAvailabilityService;
    }

    @GetMapping(path = "/getByIds", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<ItemAvailability> getItemAvailabilityByIds(@RequestParam("ids") Set<String> ids) {
        return itemAvailabilityService.getItemAvailabilityByIds(ids);
    }

    @GetMapping(path = "/getBySku", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<ItemAvailability> getItemAvailabilityBySku(@RequestParam("sku") String sku) {
        return itemAvailabilityService.getItemAvailabilityBySku(sku)
                .delayElements(Duration.ofSeconds(1));
    }
}
