package com.wineshop.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/shop/{var2}")
    public String home(@PathVariable long var2) {
        return "Welcome to the Wine Shop " + var2;
    }
    @GetMapping("/shop")
    public String shop() {
        return "Welcome in the shop";
    }
}
