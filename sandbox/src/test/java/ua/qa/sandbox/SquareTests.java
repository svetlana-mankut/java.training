package ua.qa.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by polkota on 29.05.2016.
 */
public class SquareTests {
    @Test
    public void TstArea(){
        Square s = new Square(5);
        Assert.assertEquals(s.area(),25.0);


    }
}
