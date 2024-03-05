package dev.prashant.productservicettsmorningdev24.controllers;

import dev.prashant.productservicettsmorningdev24.models.Product;
import dev.prashant.productservicettsmorningdev24.services.FakeStoreProductService;
import dev.prashant.productservicettsmorningdev24.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {


        private ProductService productService ;

        public ProductController(ProductService productService) {
            this.productService = productService;
        }





    @PostMapping("/products")
    public void createProduct() {

    }
    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id")Long productId) {
            return productService.getSingleProduct(productId);
    }
    @GetMapping("/products")
    public void getAllProduct() {
    }
    public void updateProduct() {

    }
}
