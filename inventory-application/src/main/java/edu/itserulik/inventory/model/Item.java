package edu.itserulik.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.net.URL;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Item {
    @JsonProperty("uniq_id")
    private String uniqId;
    @JsonProperty("sku")
    private String sku;
    @JsonProperty("name_title")
    private String nameTitle;
    @JsonProperty("description")
    private String description;
    @JsonProperty("list_price")
    private String listPrice;
    @JsonProperty("sale_price")
    private String salePrice;
    @JsonProperty("category")
    private String category;
    @JsonProperty("category_tree")
    private List<String> categoryTree;
    @JsonProperty("average_product_eating")
    private String averageProductRating;
    @JsonProperty("product_url")
    private URL productUrl;
    @JsonProperty("product_Image_Urls")
    private URL productImageUrls;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("total_number_reviews")
    private String totalNumberReviews;
}
