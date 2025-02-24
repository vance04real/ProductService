package zw.co.opticode.productservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import zw.co.opticode.productservice.exceptions.ProductValidationException;
import zw.co.opticode.productservice.model.Category;
import zw.co.opticode.productservice.model.Product;
import zw.co.opticode.productservice.model.Vendor;
import zw.co.opticode.productservice.service.repository.ProductRepository;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
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

        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        category.setParentCategory(null);

        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Tech Store");
        vendor.setEmail("vendor@example.com");
        vendor.setStoreName("Tech World");

        sampleProduct = new Product();
        sampleProduct.setId(1L);
        sampleProduct.setName("Test Product");
        sampleProduct.setDescription("Sample Description");
        sampleProduct.setPrice(new BigDecimal("100.00"));
        sampleProduct.setStock(10);
        sampleProduct.setCategory(category);
        sampleProduct.setVendor(vendor);
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

    @Test
    void shouldFailToCreateProductWithoutCategory() {
        // Given
        sampleProduct.setCategory(null);

        // When
        Exception exception = assertThrows(ProductValidationException.class, () -> {
            productService.createProduct(sampleProduct);
        });

        // Then
        assertThat(exception.getMessage()).isEqualTo("Product category is required");
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void shouldFailToCreateProductWithoutVendor() {
        sampleProduct.setVendor(null);

        Exception exception = assertThrows(ProductValidationException.class, () -> {
            productService.createProduct(sampleProduct);
        });

        assertThat(exception.getMessage()).isEqualTo("Product vendor is required");
        verify(productRepository, never()).save(any(Product.class));
    }

}
