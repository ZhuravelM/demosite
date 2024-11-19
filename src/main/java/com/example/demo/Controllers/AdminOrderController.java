package com.example.demo.Controllers;

import com.example.demo.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AdminOrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/admin/orders")
    public String viewOrders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "admin/orders";
    }
    @PostMapping("/admin/orders/delete")
    public String deleteOrder(@RequestParam("orderId") Long orderId) {
        orderRepository.deleteById(orderId);
        return "redirect:/admin/orders";
    }
}
