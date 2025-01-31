package br.com;

import java.math.BigDecimal;

public class Order {
    private final String userID, orderId;
    private final BigDecimal amount;

    public Order(String userId, String orderId, BigDecimal amount) {
        this.userID = userId;
        this.orderId = orderId;
        this.amount = amount;
    }
}
