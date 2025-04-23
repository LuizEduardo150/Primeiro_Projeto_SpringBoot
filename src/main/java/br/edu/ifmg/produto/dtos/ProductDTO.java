package br.edu.ifmg.produto.dtos;

import br.edu.ifmg.produto.entities.Category;
import br.edu.ifmg.produto.entities.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


public class ProductDTO extends RepresentationModel<ProductDTO> {
    @Schema(description = "Database generated ID product")
    private Long id;
    @Schema(description = "Product name")
    private String name;
    @Schema(description = "A detailed product's descriotion")
    private String description;
    @Schema(description = "Product price")
    private Double price;
    @Schema(description = "Product image")
    private String imageUrl;
    @Schema(description = "Product categories (one or more)")
    private Set<CategoryDTO> categories = new HashSet<>();

    public ProductDTO() {}


    public ProductDTO(Long id, String name, String description, Double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imageUrl = entity.getImageUrl();

        entity.getCategories().forEach(category -> this.categories.add(new CategoryDTO(category)));

    }

    public ProductDTO(Product product, Set<Category> categories) {
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

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
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
        return Objects.equals(id, product.id);
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
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", categories=" + categories +
                '}';
    }
}
