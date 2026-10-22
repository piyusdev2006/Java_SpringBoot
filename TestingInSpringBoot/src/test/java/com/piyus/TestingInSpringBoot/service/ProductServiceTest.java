package com.piyus.TestingInSpringBoot.service;

import com.piyus.TestingInSpringBoot.entity.Product;
import com.piyus.TestingInSpringBoot.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldReturnProductWhenProductExit(){

        // Arrange
        Product product = new Product(
                1L,
                "Lenevo LOQ",
                89000,
                9
        );

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        // Act

        Product actualProduct
                = productService.getProductById(1L);
        // Assert

        assertEquals(1L, actualProduct.getId());
        assertEquals("Lenevo LOQ", actualProduct.getName());


        verify(productRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenProductDoesNotExist() {
        //Arrange
        when(productRepository.findById(99L))
                .thenReturn(Optional.empty());

        //Act
        RuntimeException exception =
                assertThrows(RuntimeException.class, () ->
                        productService.getProductById(99L)
                );


        // assertion
        assertEquals("Product not found:99",
                exception.getMessage());

        verify(productRepository).findById(99L);
    }

    @Test
    void shouldCreateProductWhenNameIsUnique() {
        // Arrange
        Product request =
                new Product(null, "MacBook", 200000, 2);

        Product savedProduct =
                new Product(10L, "MacBook", 200000, 2);

        when(productRepository.existsByName("MacBook"))
                .thenReturn(false);

        when(productRepository.save(request))
                .thenReturn(savedProduct);

        // Act
        Product result =
                productService
                        .createProduct(request);

        // Assert
        assertEquals(10L, result.getId());
        assertEquals("MacBook", result.getName());

        verify(productRepository)
                .existsByName("MacBook");

        verify(productRepository)
                .save(request);
    }

    @Test
    void shouldRejectProductWhenNameAlreadyExists() {

        // Arrange
        Product request =
                new Product(null, "Keyboard", 200, 5);

        when(productRepository.existsByName("Keyboard"))
                .thenReturn(true);

        // Act and Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> productService.createProduct(request)
        );

        verify(productRepository)
                .existsByName("Keyboard");

        verify(productRepository, never())
                .save(any(Product.class));
    }
}
