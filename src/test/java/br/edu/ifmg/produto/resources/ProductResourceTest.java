package br.edu.ifmg.produto.resources;

import br.edu.ifmg.produto.dtos.ProductDTO;
import br.edu.ifmg.produto.services.ProductService;
import br.edu.ifmg.produto.util.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ProductResource.class)
class ProductResourceTest {
    // vamos mocar a camada de Service e testar a de resource


    @Autowired
    private MockMvc mockMvc; // responsavel pelas requisicoes

    @MockitoBean
    private ProductService productService;

    private ProductDTO productDTO;
    private PageImpl<ProductDTO> page;
    private Long existingId;
    private Long nonExistingId;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 2000L;
        productDTO = Factory.createProductDTO();
        productDTO.setId(existingId);
        page = new PageImpl<>(List.of(productDTO));
    }

    @Test
    void findAllShouldReturnAllPage() throws Exception {
        // Criar metodo mocado
        Mockito.when(productService.findAll(ArgumentMatchers.any())).thenReturn(page);

        // Teste da requisição
        ResultActions result = mockMvc.perform(get("/product").accept("application/json"));

        // Analisa o resultado
        result.andExpect(status().isOk());

    }

    @Test
    void findByIdShouldReturnProductWhenIdExists() throws Exception {
        // Criar metodo mocado


        Mockito.when(productService.findById(existingId)).thenReturn(productDTO);

        // Teste da requisição
        ResultActions result = mockMvc.perform(get("/product/{id}", existingId).accept("application/json"));

        // Analisa o resultado
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(existingId));
        result.andExpect(jsonPath("$.name").value(productDTO.getName()));
        result.andExpect(jsonPath("$.description").value(productDTO.getDescription()));

    }



}