package zw.co.opticode.productservice.service;

import org.junit.jupiter.api.Test;
import zw.co.opticode.productservice.model.Product;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductServiceTest {

    @Test
    void shouldCreateProductSuccessfully() {

        // Given
        Product sampleProduct = new Product();
        sampleProduct.setId(1L);
        sampleProduct.setName("Test Product");

        ProductService productService = new ProductService();

        // When
        Product createdProduct = productService.createProduct(sampleProduct);


        // Then
        assertThat(createdProduct.getId()).isNotNull();
        assertThat(createdProduct.getId()).isEqualTo(1L);
        assertThat(createdProduct.getName()).isEqualTo("Test Product");
    }

}
