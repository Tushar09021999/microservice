package com.product.controller;

import com.product.entity.Product;
import com.product.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.create(product);
    }

    @GetMapping("/{contactId}")
    public List<Product> getProductByContact(@PathVariable long contactId){

        return productService.getProductByContact(contactId);
    }

    @GetMapping()
    public List<Product> getAll(){
        return productService.getProducts();
    }

}
