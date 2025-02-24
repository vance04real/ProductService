package zw.co.opticode.productservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import zw.co.opticode.productservice.model.Product;
import zw.co.opticode.productservice.service.repository.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleProduct = new Product();
        sampleProduct.setId(1L);
        sampleProduct.setName("Test Product");
    }


    @Test
    void shouldCreateProductSuccessfully() {

        // Given

        when(productRepository.save(any(Product.class))).thenReturn(sampleProduct);

        // When
        Product createdProduct = productService.createProduct(sampleProduct);

        // Then
        assertThat(createdProduct.getId()).isNotNull();
        assertThat(createdProduct.getId()).isEqualTo(1L);
        assertThat(createdProduct.getName()).isEqualTo("Test Product");
    }

}
