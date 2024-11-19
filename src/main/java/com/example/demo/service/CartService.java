package com.example.demo.service;

import com.example.demo.models.CartItem;
import com.example.demo.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;


import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class CartService {

    private List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }
}