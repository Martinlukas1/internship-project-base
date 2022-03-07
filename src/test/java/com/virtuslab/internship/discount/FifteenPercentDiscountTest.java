package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import com.virtuslab.internship.repository.ProductDb;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FifteenPercentDiscountTest {

    //    @Disabled
    @Test
    void shouldApplyDiscount() throws Exception {
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var steak = productDb.getProduct("Steak");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 5));
        receiptEntries.add(new ReceiptEntry(steak, 1));

        var receipt = new Receipt(receiptEntries);
        var discount1 = new FifteenPercentDiscount();
        var discount2 = new TenPercentDiscount();
        var receiptAfterDiscount = discount2.apply(discount1.apply(receipt));
//        var receiptAfter2Discount = discount2.apply(receiptAfter1Discount);
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(5)).add(steak.price()).multiply(BigDecimal.valueOf(0.85)).multiply(BigDecimal.valueOf(0.9));

        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(2, receiptAfterDiscount.discounts().size());
    }

    //    @Disabled
    @Test
    void shouldApplyOnly15PercentDiscount() throws Exception {
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 2));
        receiptEntries.add(new ReceiptEntry(cereals, 1));

        var receipt = new Receipt(receiptEntries);
        var discount1 = new TenPercentDiscount();
        var discount2 = new FifteenPercentDiscount();
        var receiptAfterDiscount = discount2.apply(discount1.apply(receipt));
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(2)).add(cereals.price()).multiply(BigDecimal.valueOf(0.85));

        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }
}