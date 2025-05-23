package br.edu.ifmg.produto.util;

import br.edu.ifmg.produto.dtos.ProductDTO;
import br.edu.ifmg.produto.entities.Category;
import br.edu.ifmg.produto.entities.Product;

public class Factory {

    public static Product createProduct(){
        Product p = new Product();
        p.setName("Iphone XXX");
        p.setPrice(5000.0);
        p.setImageUrl("fakeimage.jpg");
        p.getCategories()
                .add(new Category(60L, "news"));

        return p;
    }

    public static ProductDTO createProductDTO(){
        Product p = createProduct();
        return new ProductDTO(p);
    }




}
