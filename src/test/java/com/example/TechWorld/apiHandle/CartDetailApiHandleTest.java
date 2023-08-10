package com.example.TechWorld.apiHandle;
import com.example.TechWorld.model.Cart;
import com.example.TechWorld.model.CartDetail;
import com.example.TechWorld.model.Product;
import com.example.TechWorld.repository.CartDetailRepository;
import com.example.TechWorld.repository.CartRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartDetailApiHandleTest {

    @InjectMocks
    CartDetailApiHandle cartDetailApi;

    @Mock
    CartDetailRepository cartDetailRepository;

    @Mock
    CartRepository cartRepository;

    @Mock
    ProductRepository productRepository;

    @Test
    void GivenIdNotExactValue_getByCartId_ReturnNotFound() {
        Long cartId = -1L;
        when(cartRepository.existsById(cartId)).thenReturn(false);
        ResponseEntity<List<CartDetail>> result = cartDetailApi.getAllCartDetailsByCartId(cartId);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void GivenIdExactValue_getByCartId_ResultCardExact() {
        Long cartId = 1L;
        when(cartRepository.existsById(cartId)).thenReturn(true);
        List<CartDetail> mockCartDetails = new ArrayList<>();
        when(cartDetailRepository.findByCart(any())).thenReturn(mockCartDetails);
        Cart mockCart = new Cart();
        when(cartRepository.findById(anyLong())).thenReturn(Optional.of(mockCart));
        ResponseEntity<List<CartDetail>> result = cartDetailApi.getAllCartDetailsByCartId(cartId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertSame(mockCartDetails, result.getBody());
    }

    @Test
    void GivenNonExistentId_getOne_ReturnNotFound() {
        Long cartDetailId = -1L;
        when(cartDetailRepository.existsById(cartDetailId)).thenReturn(false);
        ResponseEntity<CartDetail> result = cartDetailApi.getOneCartDetail(cartDetailId);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void GivenExistingId_getOne_ReturnCartDetail() {
        Long cartDetailId = 1L;
        CartDetail mockCartDetail = new CartDetail();
        when(cartDetailRepository.existsById(cartDetailId)).thenReturn(true);
        when(cartDetailRepository.findById(cartDetailId)).thenReturn(Optional.of(mockCartDetail));
        ResponseEntity<CartDetail> result = cartDetailApi.getOneCartDetail(cartDetailId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertSame(mockCartDetail, result.getBody());
    }

    @Test
    void GivenNonExistentCart_post_ReturnNotFound() {
        CartDetail detail = new CartDetail();
        Cart cart = new Cart();
        cart.setCartId(-1L);
        detail.setCart(cart);
        when(cartRepository.existsById(cart.getCartId())).thenReturn(false);
        ResponseEntity<CartDetail> result = cartDetailApi.post(detail);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void GivenNonExistentProduct_post_ReturnNotFound() {
        CartDetail detail = new CartDetail();
        Cart cart = new Cart();
        Product product = new Product();
        cart.setCartId(1L);
        product.setProductId(1L);
        detail.setCart(cart);
        detail.setProduct(product);
        detail.setPrice(100.0);
        when(cartRepository.existsById(cart.getCartId())).thenReturn(true);
        when(productRepository.findByStatusTrue()).thenReturn(new ArrayList<>());
        ResponseEntity<CartDetail> result = cartDetailApi.post(detail);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void GivenExistingProduct_post_ReturnUpdatedCartDetail() {
        CartDetail detail = new CartDetail();
        Cart cart = new Cart();
        Product product = new Product();
        cart.setCartId(1L);
        product.setProductId(1L);
        detail.setCart(cart);
        detail.setProduct(product);
        detail.setPrice(100.0);
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(product);
        when(cartRepository.existsById(cart.getCartId())).thenReturn(true);
        when(productRepository.findByStatusTrue()).thenReturn(mockProducts);
        when(productRepository.findByProductIdAndStatusTrue(product.getProductId())).thenReturn(product);
        Cart cartMock = new Cart();
        cartMock.setCartId(1L);
        when(cartRepository.findById(anyLong())).thenReturn(Optional.of(cartMock));
        List<CartDetail> mockCartDetails = new ArrayList<>();
        when(cartDetailRepository.findByCart(cart)).thenReturn(mockCartDetails);
        CartDetail cartDetailDbMock = new CartDetail();
        cartDetailDbMock.setQuantity(4);
        cartDetailDbMock.setPrice(400.0);
        when(cartDetailRepository.save(any())).thenReturn(cartDetailDbMock);
        ResponseEntity<CartDetail> result = cartDetailApi.post(detail);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(4, result.getBody().getQuantity());
    }

    @Test
    void GivenExistingProduct_post_ReturnNewCartDetail() {
        CartDetail detail = new CartDetail();
        Cart cart = new Cart();
        Product product = new Product();
        cart.setCartId(1L);
        product.setProductId(1L);
        detail.setCart(cart);
        detail.setProduct(product);
        detail.setPrice(100.0);
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(product);
        when(cartRepository.existsById(cart.getCartId())).thenReturn(true);
        when(productRepository.findByStatusTrue()).thenReturn(mockProducts);
        when(productRepository.findByProductIdAndStatusTrue(product.getProductId())).thenReturn(product);
        Cart cartMock = new Cart();
        cartMock.setCartId(1L);
        when(cartRepository.findById(anyLong())).thenReturn(Optional.of(cartMock));
        List<CartDetail> mockCartDetails = new ArrayList<>();
        CartDetail existingCartDetail = new CartDetail();
        existingCartDetail.setProduct(product);
        existingCartDetail.setQuantity(2);
        existingCartDetail.setPrice(200.0);
        mockCartDetails.add(existingCartDetail);
        when(cartDetailRepository.findByCart(cart)).thenReturn(mockCartDetails);
        CartDetail cartDetailDbMock = new CartDetail();
        cartDetailDbMock.setQuantity(4);
        cartDetailDbMock.setPrice(400.0);
        when(cartDetailRepository.save(any())).thenReturn(cartDetailDbMock);
        ResponseEntity<CartDetail> result = cartDetailApi.post(detail);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, mockCartDetails.size());
        assertEquals(existingCartDetail.getQuantity() + 1, result.getBody().getQuantity());
        assertEquals(existingCartDetail.getPrice() + detail.getPrice(), result.getBody().getPrice());
    }

    @Test
    void GivenNonExistentCart_put_ReturnNotFound() {
        CartDetail detail = new CartDetail();
        Cart cart = new Cart();
        cart.setCartId(-1L);
        detail.setCart(cart);
        when(cartRepository.existsById(cart.getCartId())).thenReturn(false);
        ResponseEntity<CartDetail> result = cartDetailApi.put(detail);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void GivenExistingCart_put_ReturnUpdatedCartDetail() {
        CartDetail detail = new CartDetail();
        Cart cart = new Cart();
        cart.setCartId(1L);
        detail.setCart(cart);
        when(cartRepository.existsById(cart.getCartId())).thenReturn(true);
        when(cartDetailRepository.save(detail)).thenReturn(detail);
        ResponseEntity<CartDetail> result = cartDetailApi.put(detail);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertSame(detail, result.getBody());
    }

    @Test
    void GivenNonExistentCartDetail_delete_ReturnNotFound() {
        Long cartDetailId = -1L;
        when(cartDetailRepository.existsById(cartDetailId)).thenReturn(false);
        ResponseEntity<Void> result = cartDetailApi.delete(cartDetailId);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void GivenExistingCartDetail_delete_ReturnOk() {
        Long cartDetailId = 1L;
        when(cartDetailRepository.existsById(cartDetailId)).thenReturn(true);
        ResponseEntity<Void> result = cartDetailApi.delete(cartDetailId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNull(result.getBody());
    }
}