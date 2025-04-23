package br.edu.ifmg.produto.resources;

import br.edu.ifmg.produto.dtos.ProductDTO;
import br.edu.ifmg.produto.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/product")
@Tag(name="Product", description = "Controller/Resource for products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping(produces = "application/json")
    @Operation(description = "Get All products", summary = "All products", responses = {
            @ApiResponse(description = "ok", responseCode = "200")
    })
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {

        Page<ProductDTO> products = productService.findAll(pageable);
        System.out.println("Resource retornou: " + products.getNumberOfElements());
        int a = 0;
        for (ProductDTO entity : products) {
            a ++;
            System.out.println(a);
            System.out.println(entity);
        }
        System.out.print("Fim do teste");

        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value=  "/{id}", produces = "application/json")
    @Operation(description = "Get a product", summary = "Delete product", responses = {
            @ApiResponse(description = "ok", responseCode = "201"),
            @ApiResponse(description = "Not Found", responseCode = "404"),
    })
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {

        ProductDTO product = productService.findById(id);

        return ResponseEntity.ok().body(product);
    }


    @PostMapping(produces = "application/json")
    @Operation(description = "Create a new product", summary = "create product", responses = {
            @ApiResponse(description = "created", responseCode = "201"),
            @ApiResponse(description = "Bd Request", responseCode = "400"),
            @ApiResponse(description = "unauthorized", responseCode = "401"),
            @ApiResponse(description = "Forbbiden", responseCode = "403"),
    })
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
        dto = productService.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto); // retorno 204
    }


    @PutMapping
    @Operation(description = "Update a product", summary = "update product", responses = {
            @ApiResponse(description = "ok", responseCode = "201"),
            @ApiResponse(description = "Bad Request", responseCode = "400"),
            @ApiResponse(description = "unauthorized", responseCode = "401"),
            @ApiResponse(description = "Forbbiden", responseCode = "403"),
            @ApiResponse(description = "Not Found", responseCode = "404"),
    })
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
        dto = productService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }


    @DeleteMapping(value = "/{id}")
    @Operation(description = "Delete a product", summary = "Delete product", responses = {
            @ApiResponse(description = "ok", responseCode = "201"),
            @ApiResponse(description = "Bad Request", responseCode = "400"),
            @ApiResponse(description = "unauthorized", responseCode = "401"),
            @ApiResponse(description = "Forbbiden", responseCode = "403"),
            @ApiResponse(description = "Not Found", responseCode = "404"),
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
