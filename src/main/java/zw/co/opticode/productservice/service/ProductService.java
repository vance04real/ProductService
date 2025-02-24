package zw.co.opticode.productservice.service;

import zw.co.opticode.productservice.exceptions.ProductValidationException;
import zw.co.opticode.productservice.model.Product;
import zw.co.opticode.productservice.service.repository.ProductRepository;

import java.math.BigDecimal;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        if(product.getCategory() == null) {
            throw new ProductValidationException("Product category is required");
        }

        if (product.getVendor() == null) {
            throw new ProductValidationException("Product vendor is required");
        }

        if(product.getPrice().compareTo(BigDecimal.ONE) <= 0) {
            throw new ProductValidationException("Product price must be greater than zero");
        }

        if(product.getStock() < 0){
            throw new ProductValidationException("Product stock must be zero or greater");
        }
        return productRepository.save(product);
    }
}
