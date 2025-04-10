package br.edu.ifmg.produto.services;

import br.edu.ifmg.produto.dtos.CategoryDTO;
import br.edu.ifmg.produto.dtos.ProductDTO;
import br.edu.ifmg.produto.entities.Category;
import br.edu.ifmg.produto.entities.Product;
import br.edu.ifmg.produto.repository.CategoryRepository;
import br.edu.ifmg.produto.repository.ProductRepository;
import br.edu.ifmg.produto.services.exceptions.DataBaseException;
import br.edu.ifmg.produto.services.exceptions.ResourceNotFound;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;


    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {

        Page<Product> list = productRepository.findAll(pageable);

        return list.map(c -> new ProductDTO(c));
    }


    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Optional<Product> obj = productRepository.findById(id);

        Product product =  obj.orElseThrow(() -> new ResourceNotFound("Product not found! ->" + id));

        return new ProductDTO(product);
    }


    @Transactional
    public ProductDTO insert(ProductDTO dto){

        Product entity = new Product();

        coppyDtoToEntity(dto, entity);

        entity = productRepository.save(entity);

        return new ProductDTO(entity);
    }


    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try {
            Product entity = productRepository.getReferenceById(id); // N traz os dados mas verifica se a referencia existe
            coppyDtoToEntity(dto, entity);
            entity = productRepository.save(entity); // Pode ser o metodo save mesmo, o spring é inteligente!

            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFound("Product not found! -> " + id);
        }
    }


    @Transactional
    public void delete(Long id){

        if (!productRepository.existsById(id)) {
            throw new ResourceNotFound("Product not found! -> " + id);
        }

        try {
            productRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataBaseException("Integrity violation");
        }
    }

    private void coppyDtoToEntity(ProductDTO dto, Product entity){
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImageUrl(dto.getImageUrl());
    }



}
