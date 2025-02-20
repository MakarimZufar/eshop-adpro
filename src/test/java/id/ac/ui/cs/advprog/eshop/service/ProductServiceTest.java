package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId(UUID.randomUUID().toString());
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productService.create(product);
        assertNotNull(createdProduct);
        assertEquals(product.getProductId(), createdProduct.getProductId());
        verify(productRepository).create(product);
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = Arrays.asList(product);
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> result = productService.findAll();
        assertEquals(1, result.size());
        assertEquals(product.getProductId(), result.get(0).getProductId());
        verify(productRepository).findAll();
    }

    @Test
    void testUpdateProduct_Success() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));
        Product updatedProduct = new Product();
        updatedProduct.setProductId(product.getProductId());
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(20);

        Product result = productService.updateProduct(product.getProductId(), updatedProduct);
        assertNotNull(result);
        assertEquals("Updated Product", result.getProductName());
        assertEquals(20, result.getProductQuantity());
        verify(productRepository).findAll();
    }

    @Test
    void testUpdateProduct_NotFound() {
        when(productRepository.findAll()).thenReturn(Arrays.asList());
        assertThrows(RuntimeException.class, () -> productService.updateProduct("non-existent-id", product));
        verify(productRepository).findAll();
    }

    @Test
    void testUpdateProduct_NullId() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));
        Product updatedProduct = new Product();
        updatedProduct.setProductId(null);
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(30);

        assertThrows(RuntimeException.class, () -> productService.updateProduct(null, updatedProduct));
        verify(productRepository).findAll();
    }

    @Test
    void testUpdateProduct_NullUpdatedProduct() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));

        assertThrows(RuntimeException.class, () -> productService.updateProduct(product.getProductId(), null));
        verify(productRepository).findAll();
    }

    @Test
    void testDeleteProduct() {
        when(productRepository.deleteProduct(product.getProductId())).thenReturn(true);
        boolean deleted = productService.deleteProduct(product.getProductId());
        assertTrue(deleted);
        verify(productRepository).deleteProduct(product.getProductId());
    }

    @Test
    void testDeleteProduct_NotFound() {
        when(productRepository.deleteProduct("non-existent-id")).thenReturn(false);
        boolean deleted = productService.deleteProduct("non-existent-id");
        assertFalse(deleted);
        verify(productRepository).deleteProduct("non-existent-id");
    }
}
