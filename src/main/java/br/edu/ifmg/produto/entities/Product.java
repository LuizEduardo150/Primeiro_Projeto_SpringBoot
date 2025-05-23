package br.edu.ifmg.produto.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double price;

    private String imageUrl;

    private Instant createdAt;

    private Instant updatedAt;

    // Relacionamento N para N, diferenes produtos pode ser da mesma categoria.
    // Uma categoria pode ter mais de um produto.
    @ManyToMany
    @JoinTable(
            name = "tb_product_category", joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Product() {}

    public Product(Long id, String name, String description, Double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imageUrl = entity.getImageUrl();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.categories = entity.getCategories();
    }

    public Product(Product product, Set<Category> categories) {
        this(product); // chamando o construtor com paramentro de Product
        this.categories = categories;
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

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }
    public Instant getCreatedAt() {
        return createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
    public Set<Category> getCategories() {
        return categories;
    }


    // Identificar objeto pelo ID (Chave primária)
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
