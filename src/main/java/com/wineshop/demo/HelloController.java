package com.wineshop.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String home() {
        return "Welcome to the Wine Shop ";
    }
    @GetMapping("/shop")
    public String shop() {
        return "Welcome in the shop";
    }
}
