package ua.qa;

/**
 * Created by polkota on 29.05.2016.
 */
public class Distance {
    public static void main(String[] args) {

        Point p1 = new Point(3, 9);
        Point p2 = new Point(1, 8);
        System.out.println("Расстояние между точками" + " = " + distance(p1, p2));
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p1.x - p2.x), (2)) + Math.pow((p1.y - p2.y), (2)));
    }
}
