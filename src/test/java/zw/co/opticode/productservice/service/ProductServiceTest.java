package zw.co.opticode.productservice.service;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductServiceTest {

    @Test
    void shouldCreateProductSuccessfully() {
        // Given
        Product newProduct = new Product();
        newProduct.setId(1L);
        newProduct.setName("Product 1");

        // When

        Product createdProduct = productService.createProduct(newProduct);

        // Then

        assertThat(Optional.ofNullable(createdProduct)).isNotNull();
        assertThat(createdProduct.getId()).isEqualTo(1L);
        assertThat(createdProduct.getName()).isEqualTo("Product 1");

    }
}
