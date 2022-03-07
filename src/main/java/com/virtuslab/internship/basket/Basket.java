package com.virtuslab.internship.basket;


import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.repository.ProductDb;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Basket {

    private final List<Product> products;
    private final ProductDb productDb;

    public Basket(ProductDb productDb) {
        this.productDb = productDb;
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addProductByName(String name) {
        productDb.getProduct(name);
    }

    public List<Product> getProducts() {
        return products;
    }
}
