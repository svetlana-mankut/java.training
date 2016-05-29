package ua.qa.sandbox;

/**
 * Created by polkota on 29.05.2016.
 */
public class Distance {
    public static void main(String[] args) {

        Point p1 = new Point(3, 9);
        Point p2 = new Point(1, 8);
        System.out.println("Расстояние между точками" + " = " + p1.distance(p2));
    }


}
