package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository productRepository;
    private Product product;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        product = new Product();
        product.setProductId(UUID.randomUUID().toString());
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProduct() {
        Product createdProduct = productRepository.create(product);
        assertNotNull(createdProduct);
        assertEquals(product.getProductId(), createdProduct.getProductId());
    }

    @Test
    void testFindAllProducts() {
        productRepository.create(product);
        List<Product> products = productRepository.findAll();
        assertEquals(1, products.size());
        assertEquals(product.getProductId(), products.get(0).getProductId());
    }

    @Test
    void testUpdateProduct() {
        productRepository.create(product);
        Product updatedProduct = new Product();
        updatedProduct.setProductId(product.getProductId());
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(20);

        Product result = productRepository.updateProduct(product.getProductId(), updatedProduct);
        assertNotNull(result);
        assertEquals("Updated Name", result.getProductName());
        assertEquals(20, result.getProductQuantity());

        // Verifikasi perubahan dalam daftar produk
        List<Product> products = productRepository.findAll();
        assertEquals(1, products.size());
        assertEquals("Updated Name", products.get(0).getProductName());
    }

    @Test
    void testUpdateProduct_NotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("non-existent-id");
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(20);

        Product result = productRepository.updateProduct("non-existent-id", updatedProduct);
        assertNull(result);
    }

    @Test
    void testUpdateProduct_NullId() {
        productRepository.create(product);
        Product updatedProduct = new Product();
        updatedProduct.setProductId(null);
        updatedProduct.setProductName("Null ID Product");
        updatedProduct.setProductQuantity(5);

        Product result = productRepository.updateProduct(null, updatedProduct);
        assertNull(result);
    }

    @Test
    void testUpdateProduct_WithSameIdDifferentObject() {
        productRepository.create(product);
        Product updatedProduct = new Product();
        updatedProduct.setProductId(product.getProductId());
        updatedProduct.setProductName("New Name");
        updatedProduct.setProductQuantity(50);

        Product result = productRepository.updateProduct(product.getProductId(), updatedProduct);
        assertNotNull(result);
        assertEquals("New Name", result.getProductName());
        assertEquals(50, result.getProductQuantity());

        List<Product> products = productRepository.findAll();
        assertEquals(1, products.size());
        assertEquals("New Name", products.get(0).getProductName());
        assertEquals(50, products.get(0).getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        productRepository.create(product);
        boolean deleted = productRepository.deleteProduct(product.getProductId());
        assertTrue(deleted);
        assertTrue(productRepository.findAll().isEmpty());
    }

    @Test
    void testDeleteProduct_NotFound() {
        boolean deleted = productRepository.deleteProduct("non-existent-id");
        assertFalse(deleted);
    }
}
