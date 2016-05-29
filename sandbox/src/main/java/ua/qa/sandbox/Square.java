package ua.qa.sandbox;

/**
 * Created by polkota on 28.05.2016.
 */
public class Square {
    public double l;

    public Square(double l) {
        this.l = l;
    }

    public double area() {
        return this.l * this.l;
    }
}
