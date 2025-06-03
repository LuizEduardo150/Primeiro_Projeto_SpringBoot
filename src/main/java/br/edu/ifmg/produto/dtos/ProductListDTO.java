package br.edu.ifmg.produto.dtos;

import br.edu.ifmg.produto.entities.Category;
import br.edu.ifmg.produto.entities.Product;
import br.edu.ifmg.produto.projections.ProductProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


public class ProductListDTO extends RepresentationModel<ProductDTO> {
    @Schema(description = "Database generated ID product")
    private Long id;
    @Schema(description = "Product name")
    private String name;
    @Schema(description = "Product price")
    private Double price;
    @Schema(description = "Product image")
    private String imageUrl;
    @Schema(description = "Product categories (one or more)")
    private Set<CategoryDTO> categories = new HashSet<>();

    public ProductListDTO() {}


    public ProductListDTO(Long id, String name, Double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ProductListDTO(ProductProjection entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.imageUrl = entity.getImageUrl();
        

        entity.getCategories().forEach(category -> this.categories.add(new CategoryDTO(category)));

    }

    public ProductListDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.imageUrl = entity.getImageUrl();

        entity.getCategories().forEach(category -> this.categories.add(new CategoryDTO(category)));

    }

    public ProductListDTO(Product product, Set<Category> categories) {
        this(product); // chamando o construtor com paramentro de Product

        // Duas maneiras de fazer o map de Product para ProductDTO
        //this.categories = categories.stream().map(CategoryDTO::new).collect(Collectors.toSet());
        categories.forEach(c -> this.categories.add(new CategoryDTO(c)));
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getPrice() {
        return price;
    }

    public void setCategories(Set<CategoryDTO> categories) {
        this.categories = categories;
    }
    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getImageUrl() {
        return imageUrl;
    }


    // Identificar objeto pelo ID (Chave primária)
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductDTO product)) return false;
        return Objects.equals(id, product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", categories=" + categories +
                '}';
    }
}
