package com.product.service;

import com.product.entity.Product;

import java.util.List;

public interface ProductService {

    Product create(Product product);

    List<Product> getProducts();

    List<Product> getProductByContact(long contactId);
}
