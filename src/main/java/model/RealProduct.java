package model;

public class RealProduct extends Product {
    private double size;
    private double weight;

    public RealProduct(double price, String name, double size, double weight) {
        super(price, name);
        this.size = size;
        this.weight = weight;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}
