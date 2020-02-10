package edu.itserulik.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Item {
    private Boolean available;
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
    private String totalNumberReviews;
}
