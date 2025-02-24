package zw.co.opticode.productservice.service;

import zw.co.opticode.productservice.exceptions.ProductValidationException;
import zw.co.opticode.productservice.model.Product;
import zw.co.opticode.productservice.service.repository.ProductRepository;

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
        return productRepository.save(product);
    }
}
