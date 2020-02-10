package edu.itserulik.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.net.URL;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDto {
    private String uniqId;
    private String sku;
    private String nameTitle;
    private String description;
    private String listPrice;
    private String salePrice;
    private String category;
    private List<String> categoryTree;
    private String averageProductRating;
    private URL productUrl;
    private URL productImageUrls;
    private String brand;
    private Integer totalNumberReviews;
}
