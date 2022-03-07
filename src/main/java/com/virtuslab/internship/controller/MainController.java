package com.virtuslab.internship.controller;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    private final Basket basket;
    private final ReceiptGenerator receiptGenerator;

    public MainController(Basket basket, ReceiptGenerator receiptGenerator) {
        this.basket = basket;
        this.receiptGenerator = receiptGenerator;
    }

    @GetMapping("/receipt")
    public Receipt getReceipt(@RequestBody Basket basket) {
        return receiptGenerator.generate(basket);
    }

    @PostMapping("/addProduct/{name}")
    public void addProd(@PathVariable String name) {
        basket.addProductByName(name);
    }

}
