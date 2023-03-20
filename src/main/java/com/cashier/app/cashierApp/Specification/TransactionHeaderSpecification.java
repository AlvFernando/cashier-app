package com.cashier.app.cashierApp.Specification;

import org.springframework.data.jpa.domain.Specification;

import com.cashier.app.cashierApp.Model.Entity.PaymentMethod;
import com.cashier.app.cashierApp.Model.Entity.TransactionHeader;

import jakarta.persistence.criteria.Join;


public class TransactionHeaderSpecification {

    public static Specification<TransactionHeader> customHeader(String startDate,String endDate){
        return (root, query, cb) -> cb.between(root.<String>get("transactionDate"), startDate, endDate);
    }

    public static Specification<TransactionHeader> customHeaderJoined() {
        return (root, query, criteriaBuilder) -> {
            Join<PaymentMethod,TransactionHeader> authorsBook = root.join("paymentMethod");
            //criteriaBuilder.between(root.get("transactiondate"), startDate, endDate);
            return criteriaBuilder.greaterThan(authorsBook.get("id"), 0);
        };
    }
}
