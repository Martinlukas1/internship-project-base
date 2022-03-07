package com.virtuslab.internship.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public record Receipt(
        List<ReceiptEntry> entries,
        List<String> discounts,
        BigDecimal totalPrice) {
    @Autowired
    public Receipt(List<ReceiptEntry> entries) {
        this(entries,
                new ArrayList<>(),
                entries.stream()
                        .map(receiptEntry -> receiptEntry.totalPrice())
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }
}
