package dev.prashant.productservicettsmorningdev24.services;

import dev.prashant.productservicettsmorningdev24.dtos.FakeStoreProductdto;
import dev.prashant.productservicettsmorningdev24.models.Product;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductResponse = restTemplate.getForEntity(
                    "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductdto.class
            );

        FakeStoreProductDto fakeStoreProduct = fakeStoreProductResponse.getBody();

        if (fakeStoreProduct == null) {
            throw new ProductNotFoundException("Product with id: " + productId + " doesn't exist. Retry some other product.");
        }

        return fakeStoreProduct.toProduct();
    }
    @Override
    public List<Product> getProducts() {
        FakeStoreProductDto[] fakeStoreProducts =
                restTemplate.getForObject(
                        "https://fakestoreapi.com/products",
                        FakeStoreProductDto[].class
                );

        List<Product> products = new ArrayList<>();

        for (FakeStoreProductDto fakeStoreProduct: fakeStoreProducts) {
            products.add(fakeStoreProduct.toProduct());
        }

        return products;
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setDescription(description);

        FakeStoreProductDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products", // url
                fakeStoreProductDto, // request body
                FakeStoreProductDto.class // data type of response
        );

        if (response == null) return new Product();

        return response.toProduct();
    }
}
