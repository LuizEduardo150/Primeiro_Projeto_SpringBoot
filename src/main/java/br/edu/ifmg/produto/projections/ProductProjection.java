package br.edu.ifmg.produto.projections;

import br.edu.ifmg.produto.entities.Category;

import java.util.Set;

public interface ProductProjection {
    Long getId();
    String getName();
    String getImageUrl();
    Double getPrice();

    Set<Category> getCategories();

}
