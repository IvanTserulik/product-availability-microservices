package edu.itserulik.catalog.controller;

import edu.itserulik.catalog.model.ItemDto;
import edu.itserulik.catalog.service.ItemProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Set;

@RestController
public class ItemController {
    private final ItemProviderService itemProviderService;
    private final ModelMapper modelMapper = new ModelMapper();

    public ItemController(ItemProviderService itemProviderService) {
        this.itemProviderService = itemProviderService;
    }

    @GetMapping(path = "/getByIds", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<ItemDto> getItemByIds(@RequestParam("ids") Set<String> ids) {
        return itemProviderService.getItemByIds(ids)
                .map(item -> modelMapper.map(item, ItemDto.class));
    }

    @GetMapping(path = "/getBySku", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<ItemDto> getItemsBySku(@RequestParam("sku") String sku) {
        return itemProviderService.getItemsBySku(sku)
                .map(item -> modelMapper.map(item, ItemDto.class))
                .delayElements(Duration.ofSeconds(1));
    }

}
