package com.example.demo.Controllers;

import com.example.demo.models.Cart;
import com.example.demo.models.CartItem;
import com.example.demo.models.Product;
import com.example.demo.repo.CartRepository;
import com.example.demo.repo.CartItemRepository;
import com.example.demo.repo.ProductRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            CartItem existingItem = null;
            for (CartItem item : cart.getItems()) {
                if (item.getProduct().getId().equals(productId)) {
                    existingItem = item;
                    break;
                }
            }

            if (existingItem != null) {
                existingItem.setQuantity(existingItem.getQuantity() + 1);
            } else {
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setQuantity(1);
                cart.addItem(cartItem);
            }
        }

        return "redirect:/products";
    }

    @GetMapping
    public String showCart(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        model.addAttribute("cart", cart);
        return "cart";
    }

}


