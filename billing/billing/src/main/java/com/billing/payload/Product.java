package com.billing.payload;

import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private long productId;

    private long contactId;

    private String productName;
    private int productPrice;
    private int quantity;
}
