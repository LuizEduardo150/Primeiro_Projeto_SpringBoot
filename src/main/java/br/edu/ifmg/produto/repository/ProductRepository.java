package br.edu.ifmg.produto.repository;


import br.edu.ifmg.produto.entities.Product;
import br.edu.ifmg.produto.projections.ProductProjection;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = """
            SELECT * from
            (
            SELECT DISTINCT p.id, p.name, p.image_url, p.price
            INNER JOIN tb_prduct_category pc on pc
            FROM tb_product p
            WHERE (:categoriesID IS NULL ||  pc.ategory_id in :categoriesID ) and LOWER(p.name) like LOWER(CONCAT('%', :name, '%'))
            ) as tb_result
            """, countQuery = """
            
            select count(*) from
            (
            SELECT DISTINCT p.id, p.name, p.image_url, p.price
            INNER JOIN tb_prduct_category pc on pc
            FROM tb_product p
            WHERE (:categoriesID IS NULL ||  pc.ategory_id in :categoriesID ) and LOWER(p.name) like LOWER(CONCAT('%', :name, '%'))
            ) as tb_result
            """)
    public Page<ProductProjection> searchProducts(List<Long> categoriesId, String name, Pageable pageable);


    public void findAllPaged(){

    }


}
