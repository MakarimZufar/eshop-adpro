package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    private Product product;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        product = new Product();
        product.setProductId(UUID.randomUUID().toString());
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        assertEquals("createProduct", viewName);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        String viewName = productController.createProductPost(product, model);
        assertEquals("redirect:/product/list", viewName);
        verify(productService).create(any(Product.class));
    }

    @Test
    void testProductListPage() {
        List<Product> products = Arrays.asList(product);
        when(productService.findAll()).thenReturn(products);

        String viewName = productController.productListPage(model);
        assertEquals("productList", viewName);
        verify(model).addAttribute("products", products);
    }

    @Test
    void testEditProductPage_Default() {
        String viewName = productController.editProductPage(model);
        assertEquals("editProduct", viewName);
    }

    @Test
    void testEditProductPage_WithId() {
        when(productService.findAll()).thenReturn(Arrays.asList(product));

        String viewName = productController.editProductPage(product.getProductId(), model);
        assertEquals("editProduct", viewName);
        verify(model).addAttribute("product", product);
    }

    @Test
    void testEditProductPage_InvalidId() {
        when(productService.findAll()).thenReturn(Arrays.asList(product));

        String viewName = productController.editProductPage("invalid-id", model);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPost() {
        String viewName = productController.editProductPost(product.getProductId(), product);
        assertEquals("redirect:/product/list", viewName);
        verify(productService).updateProduct(product.getProductId(), product);
    }

    @Test
    void testDeleteProduct() {
        String viewName = productController.deleteProduct(product.getProductId());
        assertEquals("redirect:/product/list", viewName);
        verify(productService).deleteProduct(product.getProductId());
    }
}
