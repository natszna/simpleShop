package pl.natalia.simpleShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.natalia.simpleShop.model.Order;
import pl.natalia.simpleShop.repository.OrderRepository;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @ModelAttribute("orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders")
    public String showAllOrders() {
        return "order/list";
    }
}
