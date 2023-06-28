package model;

import java.time.LocalDate;

public class VirtualProduct extends Product {
    private LocalDate expirationDate;
    private String code;

    public VirtualProduct(double price, String name, LocalDate expirationDate, String code) {
        super(price, name);
        this.expirationDate = expirationDate;
        this.code = code;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public double getWeight() {
        return 0;
    }
}
