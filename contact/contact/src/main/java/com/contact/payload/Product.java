package com.contact.payload;

import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    private long productId;

    private long contactId;

    private String productName;

    private int productPrice;

    private int quantity;

}
