package com.cafe.controller;

import com.cafe.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductMapper productMapper;

    @GetMapping("/")
    public String home(Model model) {
        java.util.List<com.cafe.entity.Product> list = productMapper.findAll();
        model.addAttribute("productList", list);
        return "product/list";
    }
}
