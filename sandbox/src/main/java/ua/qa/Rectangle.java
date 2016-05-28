package ua.qa;

import org.w3c.dom.css.Rect;

/**
 * Created by polkota on 28.05.2016.
 */
public class Rectangle {
    public double a;
    public double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double area() {
        return this.a * this.b;
    }
}

