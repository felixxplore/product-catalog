package com.project.catalog.dto;

import com.project.catalog.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Product toEntity(ProductRequestDTO dto);
    ProductResponseDTO toResponseDTO(Product product);
}
