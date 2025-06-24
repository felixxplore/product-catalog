package com.project.catalog.repository;

import com.project.catalog.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{'category': ?0, 'price': {$gte: ?1, $lte: ?2}, 'inStock': ?3}")
    List<Product> findByFilters(String category, Double minPrice, Double maxPrice, Boolean inStock);
}
