package com.example.TechWorld.apiHandle;

import com.example.TechWorld.model.Cart;
import com.example.TechWorld.model.User;
import com.example.TechWorld.repository.CartRepository;
import com.example.TechWorld.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartApiHandleTest {

    @InjectMocks
    CartApiHandle cartApi;

    @Mock
    CartRepository cartRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    void GivenEmailNotFound_getCartUser_ResultNotFoundMessage() {
        String emailInput = "";
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        ResponseEntity<Cart> result = cartApi.getCartUser(emailInput);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void GivenEmailExactValue_getCartUser_ResultCartObject() {
        String emailInput = "existing_email@example.com";
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        Cart mockCart = new Cart();
        when(cartRepository.findByUser(any())).thenReturn(mockCart);
        User mockUser = new User();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(mockUser));
        ResponseEntity<Cart> result = cartApi.getCartUser(emailInput);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertSame(mockCart, result.getBody());
    }

    @Test
    void GivenEmailNotFound_putCartUser_ResultNotFoundMessage() {
        String emailInput = "";
        Cart cart = new Cart();
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        ResponseEntity<Cart> result = cartApi.putCartUser(emailInput, cart);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void GivenEmailFound_putCartUser_ResultCartObject() {
        String emailInput = "existing_email@example.com";
        Cart cart = new Cart();
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        Cart mockSavedCart = new Cart();
        when(cartRepository.save(any())).thenReturn(mockSavedCart);
        ResponseEntity<Cart> result = cartApi.putCartUser(emailInput, cart);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertSame(mockSavedCart, result.getBody());
    }
}