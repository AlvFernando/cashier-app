package com.cashier.app.cashierApp.Model.Request;

public class PaymentMethodRequest {
    private Integer id;
    private String paymentMethodName;

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    @Override
    public String toString() {
        return "PaymentMethodRequest [paymentMethodName=" + paymentMethodName + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
