package com.piyus.TestingInSpringBoot.controller;

import com.piyus.TestingInSpringBoot.entity.Product;
import com.piyus.TestingInSpringBoot.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void shouldReturnProductIfProductExist() throws Exception {
        // arrange
        Product product =
                new Product(1L, "Lenevo LOQ", 89000, 9);

        when(productService.getProductById(1L))
                .thenReturn(product);

        //Act and assertion
        mockMvc.perform(get("/api/product/1")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(
                        MediaType.APPLICATION_JSON
                ))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Lenevo LOQ"))
                .andExpect(jsonPath("$.price").value(89000))
                .andExpect(jsonPath("$.stock").value(9));

        verify(productService).getProductById(1L);
    }
}