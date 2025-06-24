package com.project.catalog.service;

import com.project.catalog.dto.ProductMapper;
import com.project.catalog.dto.ProductRequestDTO;
import com.project.catalog.dto.ProductResponseDTO;
import com.project.catalog.exceptions.ResourceNotFoundException;
import com.project.catalog.model.Product;
import com.project.catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;


    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        Product product = mapper.toEntity(dto);
        return mapper.toResponseDTO(repository.save(product));
    }

    public List<ProductResponseDTO> getAllProducts(String category, Double minPrice, Double maxPrice, Boolean inStock) {
        if (category != null || minPrice != null || maxPrice != null || inStock != null) {
            minPrice = minPrice != null ? minPrice : 0.0;
            maxPrice = maxPrice != null ? maxPrice : Double.MAX_VALUE;
            return repository.findByFilters(category, minPrice, maxPrice, inStock)
                    .stream()
                    .map(mapper::toResponseDTO)
                    .collect(Collectors.toList());
        }
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO getProductById(String id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return mapper.toResponseDTO(product);
    }

    public ProductResponseDTO updateProduct(String id, ProductRequestDTO dto) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        Product updated = mapper.toEntity(dto);
        updated.setId(id);
        updated.setCreatedAt(existing.getCreatedAt());
        return mapper.toResponseDTO(repository.save(updated));
    }

    public void deleteProduct(String id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
