package com.example.TechWorld.apiHandle;

import com.example.TechWorld.model.Category;
import com.example.TechWorld.model.Product;
import com.example.TechWorld.repository.CategoryRepository;
import com.example.TechWorld.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductApiHandleTest {

    @InjectMocks
    ProductApiHanle productApi;

    @Mock
    ProductRepository repo;

    @Mock
    CategoryRepository cRepo;


    @Test
    void GivenUsersExist_getAll_ReturnProductsList() {
        Product pro1 = new Product();
        pro1.setProductId(1L);
        pro1.setStatus(true);
        Product pro2 = new Product();
        pro2.setProductId(1L);
        pro2.setStatus(true);
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(pro1);
        mockProducts.add(pro2);

        when(repo.findByStatusTrue()).thenReturn(mockProducts);
        ResponseEntity<List<Product>> result = productApi.getAllProducts();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(mockProducts.size(), result.getBody().size());
        assertSame(mockProducts, result.getBody());
    }

    @Test
    void GivenUsersExist_getBestSeller_ReturnProductsList() {
        Product pro1 = new Product();
        pro1.setProductId(1L);
        pro1.setStatus(true);
        Product pro2 = new Product();
        pro2.setProductId(1L);
        pro2.setStatus(true);
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(pro1);
        mockProducts.add(pro2);

        when(repo.findByStatusTrueOrderBySoldDesc()).thenReturn(mockProducts);
        ResponseEntity<List<Product>> result = productApi.getBestSellingProducts();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(mockProducts.size(), result.getBody().size());
        assertSame(mockProducts, result.getBody());
    }

    @Test
    void GivenUsersExist_getBestSellerAdmin_ReturnProductsList() {
        Product pro1 = new Product();
        pro1.setProductId(1L);
        pro1.setStatus(true);
        Product pro2 = new Product();
        pro2.setProductId(1L);
        pro2.setStatus(true);
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(pro1);
        mockProducts.add(pro2);

        when(repo.findTop10ByOrderBySoldDesc()).thenReturn(mockProducts);
        ResponseEntity<List<Product>> result = productApi.getBestSellingProductsForAdmin();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(mockProducts.size(), result.getBody().size());
        assertSame(mockProducts, result.getBody());
    }

    @Test
    void GivenUsersExist_getLasted_ReturnProductsList() {
        Product pro1 = new Product();
        pro1.setProductId(1L);
        pro1.setStatus(true);
        Product pro2 = new Product();
        pro2.setProductId(1L);
        pro2.setStatus(true);
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(pro1);
        mockProducts.add(pro2);

        when(repo.findByStatusTrueOrderByEnteredDateDesc()).thenReturn(mockProducts);
        ResponseEntity<List<Product>> result = productApi.getLatestProducts();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(mockProducts.size(), result.getBody().size());
        assertSame(mockProducts, result.getBody());
    }

    @Test
    void GivenUsersExist_getRated_ReturnProductsList() {
        Product pro1 = new Product();
        pro1.setProductId(1L);
        pro1.setStatus(true);
        Product pro2 = new Product();
        pro2.setProductId(1L);
        pro2.setStatus(true);
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(pro1);
        mockProducts.add(pro2);

        when(repo.findProductRated()).thenReturn(mockProducts);
        ResponseEntity<List<Product>> result = productApi.getRatedProducts();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(mockProducts.size(), result.getBody().size());
        assertSame(mockProducts, result.getBody());
    }


    @Test
    void GivenUsersExist_suggest_ReturnProductsList() {
        Long categoryId = 1L;
        Long productId = 2L;

        Product pro1 = new Product();
        pro1.setProductId(1L);
        pro1.setStatus(true);
        Product pro2 = new Product();
        pro2.setProductId(1L);
        pro2.setStatus(true);
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(pro1);
        mockProducts.add(pro2);

        when(repo.findProductSuggest(categoryId, productId, categoryId, categoryId)).thenReturn(mockProducts);
        ResponseEntity<List<Product>> result = productApi.suggestProducts(categoryId, productId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(mockProducts.size(), result.getBody().size());
        assertSame(mockProducts, result.getBody());
    }

    @Test
    void GivenUsersExist_getByCategory_ReturnProductsList() {
        Long categoryId = 1L;

        Category category = new Category();
        category.setCategoryId(categoryId);

        Product pro1 = new Product();
        pro1.setProductId(1L);
        pro1.setStatus(true);

        Product pro2 = new Product();
        pro2.setProductId(2L);
        pro2.setStatus(true);

        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(pro1);
        mockProducts.add(pro2);

        when(cRepo.existsById(categoryId)).thenReturn(true);
        when(cRepo.findById(categoryId)).thenReturn(Optional.of(category));
        when(repo.findByCategory(category)).thenReturn(mockProducts);

        ResponseEntity<List<Product>> result = productApi.getProductsByCategory(categoryId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(mockProducts.size(), result.getBody().size());
        assertSame(mockProducts, result.getBody());
    }

    @Test
    void givenInvalidCategoryId_shouldReturnNotFound() {
        Long categoryId = 1L;
        when(cRepo.existsById(categoryId)).thenReturn(false);
        ResponseEntity<List<Product>> response = productApi.getProductsByCategory(categoryId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void GivenNonExistentProductId_getOne_ReturnNotFound() {
        Long productId = -1L;
        when(repo.existsById(productId)).thenReturn(false);
        ResponseEntity<Product> result = productApi.getProductById(productId);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void GivenExistingProductId_getOne_ReturnProduct() {
        Long productId = 1L;
        Product mockProduct = new Product();
        mockProduct.setProductId(productId);
        when(repo.existsById(productId)).thenReturn(true);
        when(repo.findById(productId)).thenReturn(Optional.of(mockProduct));

        ResponseEntity<Product> result = productApi.getProductById(productId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertSame(mockProduct, result.getBody());
    }



    @Test
    void GivenNewProduct_post_ReturnOkResponse() {
        Product newProduct = new Product();
        newProduct.setProductId(1L);
        newProduct.setName("Test Product");
        newProduct.setPrice(100.0);
        newProduct.setDescription("Test description");
        newProduct.setCategory(new Category());

        when(repo.existsById(newProduct.getProductId())).thenReturn(false);
        when(repo.save(newProduct)).thenReturn(newProduct);

        ResponseEntity<Product> result = productApi.createProduct(newProduct);
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(newProduct, result.getBody());
    }

    @Test
    void GivenExistingProduct_post_ReturnBadRequestResponse() {
        Product existingProduct = new Product();
        existingProduct.setProductId(1L);
        existingProduct.setName("Existing Product");
        existingProduct.setPrice(200.0);
        existingProduct.setDescription("Existing description");
        existingProduct.setCategory(new Category());

        when(repo.existsById(existingProduct.getProductId())).thenReturn(true);
        ResponseEntity<Product> result = productApi.createProduct(existingProduct);

        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void GivenMatchingProductId_put_ReturnOkResponse() {
        Long productId = 1L;

        Product product = new Product();
        product.setProductId(productId);

        Product existingProduct = new Product();
        existingProduct.setProductId(productId);
        existingProduct.setName("Existing Product");
        existingProduct.setPrice(200.0);
        existingProduct.setDescription("Existing description");
        existingProduct.setCategory(new Category());

        when(repo.existsById(productId)).thenReturn(true);
        when(repo.save(product)).thenReturn(product);

        ResponseEntity<Product> result = productApi.put(productId, product);
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(product, result.getBody());
    }

    @Test
    void GivenMismatchedProductId_put_ReturnBadRequestResponse() {
        Long productId = 1L;

        Product product = new Product();
        product.setProductId(2L);

        ResponseEntity<Product> result = productApi.put(productId, product);
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void GivenNonExistingProductId_put_ReturnNotFoundResponse() {
        Long productId = 1L;

        Product product = new Product();
        product.setProductId(productId);

        when(repo.existsById(productId)).thenReturn(false);
        ResponseEntity<Product> result = productApi.put(productId, product);
        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void GivenExistingProductId_delete_ReturnOkResponse() {
        Long productId = 1L;

        when(repo.existsById(productId)).thenReturn(true);
        Product product = new Product();
        product.setProductId(productId);
        when(repo.findById(productId)).thenReturn(Optional.of(product));
        ResponseEntity<Void> result = productApi.delete(productId);
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNull(result.getBody());
        verify(repo).save(product);
    }

    @Test
    void GivenNonExistingProductId_delete_ReturnNotFoundResponse() {
        Long productId = 1L;

        when(repo.existsById(productId)).thenReturn(false);
        ResponseEntity<Void> result = productApi.delete(productId);
        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
        verify(repo, never()).save(any());
    }
}