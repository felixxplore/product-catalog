package com.project.catalog.service;


import com.project.catalog.dto.ProductMapper;
import com.project.catalog.dto.ProductRequestDTO;
import com.project.catalog.dto.ProductResponseDTO;
import com.project.catalog.exceptions.ResourceNotFoundException;
import com.project.catalog.model.Product;
import com.project.catalog.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductService service;

    private Product product;
    private ProductRequestDTO requestDTO;
    private ProductResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId("1");
        product.setName("Test Product");
        product.setPrice(10.0);
        product.setCategory("Test");
        product.setInStock(true);

        requestDTO = new ProductRequestDTO();
        requestDTO.setName("Test Product");
        requestDTO.setPrice(10.0);
        requestDTO.setCategory("Test");
        requestDTO.setInStock(true);

        responseDTO = new ProductResponseDTO();
        responseDTO.setId("1");
        responseDTO.setName("Test Product");
        responseDTO.setPrice(10.0);
        responseDTO.setCategory("Test");
        responseDTO.setInStock(true);
    }

    @Test
    void createProduct_Success() {
        when(mapper.toEntity(any(ProductRequestDTO.class)))
                .thenReturn(product);
        when(repository.save(any(Product.class))).thenReturn(product);
        when(mapper.toResponseDTO(any(Product.class))).thenReturn(responseDTO);

        ProductResponseDTO result = service.createProduct(requestDTO);

        assertNotNull(result);
        assertEquals("1", result.getId());
        verify(repository).save(product);
    }

    @Test
    void getProductById_Success() {
        when(repository.findById("1")).thenReturn(Optional.of(product));
        when(mapper.toResponseDTO(any(Product.class))).thenReturn(responseDTO);

        ProductResponseDTO result = service.getProductById("1");

        assertNotNull(result);
        assertEquals("1", result.getId());
        verify(repository).findById("1");
    }

    @Test
    void getProductById_NotFound() {
        when(repository.findById("1")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getProductById("1"));
    }
}