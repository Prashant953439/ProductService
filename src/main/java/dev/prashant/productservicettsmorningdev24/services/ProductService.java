package dev.prashant.productservicettsmorningdev24.services;

import dev.prashant.productservicettsmorningdev24.models.Product;

public interface ProductService {
    List<Product> getProducts();
    Product getSingleProduct(Long productId);




}
