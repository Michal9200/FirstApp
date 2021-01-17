package pl.sda.finalapp.products;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {

    public Product() {
    }

    public Product(String title, String pictureUrl, BigDecimal price, ProductType productType, Integer categoryId) {
        this.title = title;
        this.pictureUrl = pictureUrl;
        this.price = price;
        this.productType = productType;
        this.categoryId = categoryId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String title;
    private String pictureUrl;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private Integer categoryId;

    public ProductListDto toListDto(String categoryName){
        return new ProductListDto( this.id,
                this.title,
                this.pictureUrl,
                this.price,
                this.productType,
                categoryName);
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public static Product fromDto(ProductDto dto){
        Product product = new Product();
        product.title = dto.getTitle();
        product.pictureUrl = dto.getPictureUrl();
        product.price = dto.getPrice();
        product.productType = dto.getProductType();
        product.categoryId = dto.getCategoryId();
        return product;
    }

    public ProductDto toDto(){
        return new ProductDto(
                this.id,
                this.title,
                this.pictureUrl,
                this.price,
                this.productType,
                this.categoryId);
    }


    public void apply(ProductDto dto) {
        this.title = dto.getTitle();
        this.pictureUrl = dto.getPictureUrl();
        this.price = dto.getPrice();
        this.productType = dto.getProductType();
        this.categoryId = dto.getCategoryId();
    }
}
