package zw.co.opticode.productservice.service;

import zw.co.opticode.productservice.model.Product;
import zw.co.opticode.productservice.service.repository.ProductRepository;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
