package com.example.elasticplugin.controller;

import com.example.elasticplugin.model.Product;
import com.example.elasticplugin.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping("/get/{id}")
    public Product getProduct(@PathVariable String id){
        return productService.getProduct(id).orElseThrow();

    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @GetMapping()
    public Iterable<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/suggest/{name}")
    public List<Product> autoSuggestItemsByNameWithQuery(@PathVariable String name) {
        return productService.autoSuggestItemsByNameWithQuery(name);
    }
}
