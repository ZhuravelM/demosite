package com.example.demo.Controllers;

import com.example.demo.models.Order;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/submit")
    public String submitOrder(@RequestParam("contactInfo") String contactInfo, Model model) {

        var cartItems = cartService.getCartItems();

        Order order = new Order();
        order.setContactInfo(contactInfo);
        order.setItems(cartItems);

        orderService.saveOrder(order);

        cartService.clearCart();

        model.addAttribute("message", "Ваше замовлення прийняте!");

        return "order-confirmation";
    }
}
