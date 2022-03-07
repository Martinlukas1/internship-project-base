package com.virtuslab.internship.receipt;


import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        return getReceipt(basket);
    }

    private Receipt getReceipt(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        for (Product prod : basket.getProducts()) {
            int quantity = 0;
            if (basket.getProducts().size() > 0) quantity = quantityCounter(basket.getProducts(), prod);
            ReceiptEntry receiptEntry = new ReceiptEntry(prod, quantity);
            if (!receiptEntries.contains(receiptEntry)) receiptEntries.add(receiptEntry);
        }
        return new Receipt(receiptEntries);
    }

    private int quantityCounter(List<Product> products, Product product) {
        return (int) products.stream().filter(product::equals).count();
    }
}
