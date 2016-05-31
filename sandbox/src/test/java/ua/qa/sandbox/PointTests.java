package ua.qa.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by polkota on 31.05.2016.
 */
public class PointTests {
    @Test
    //failed test
    public void checkDistance() {
        Point p1 = new Point(3, 9);
        Point p2 = new Point(1, 8);
        Assert.assertEquals(p1.distance(p2), 3.0);

    }

    @Test
    public void checkDistance2() {
        Point p1 = new Point(3, 9);
        Point p2 = new Point(1, 8);
        Assert.assertEquals(p1.distance(p2), 2.23606797749979);
    }

    @Test
    public void checkDistance3() {
        Point p1 = new Point(3, 9);
        Point p2 = new Point(1, 8);
        Assert.assertTrue(p1.distance(p2) == 2.23606797749979);
    }

    @Test
    public void checkDistance4() {
        Point p1 = new Point(3, 9);
        Point p2 = new Point(1, 8);
        Assert.assertFalse(p1.distance(p2) != 2.23606797749979);
    }

    @Test
    public void checkDistance5() {
        Point p1 = new Point(3, 9);
        Point p2 = new Point(1, 8);
        if (p1.distance(p2) == 2.23606797749979) {
            System.out.println("Test passed");
        } else System.out.println("Test failed: distance doesn'n match");
    }
}

