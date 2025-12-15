package com.cafe.controller;

import com.cafe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductRepository productRepository;

    @GetMapping("/")
    public String home(Model model) {
        java.util.List<com.cafe.entity.Product> list = productRepository.findAll();
        System.out.println("DEBUG: Product List Size: " + list.size());
        if (!list.isEmpty()) {
            System.out.println("DEBUG: First Product: " + list.get(0));
        }
        model.addAttribute("productList", list);
        return "product/list";
    }
}
