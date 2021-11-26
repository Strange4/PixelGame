package game.util;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2DTests {
    @Test
    public void vectorInstance(){
        new Vector2D(1,1);
    }
    @Test
    public void getters(){
        Vector2D v = new Vector2D(-509, 1000);
        assertEquals(-509, v.getX());
        assertEquals(1000, v.getY());

        v = new Vector2D(20.5, -13.2);
        assertEquals(20.5, v.getX());
        assertEquals(-13.2, v.getY());
    }

    @Test
    public void setters(){
        Vector2D v = new Vector2D(-509, 1000);
        v.setX(-14.5);
        v.setY(-0.2);
        assertEquals(-14.5, v.getX());
        assertEquals(-0.2, v.getY());

        v = new Vector2D(0, -0);
        v.setX(2);
        v.setY(2.3);
        assertEquals(2, v.getX());
        assertEquals(2.3, v.getY());
    }

    @Test
    public void vectorAddition(){
        Vector2D v = new Vector2D(1,1);
        Vector2D a = new Vector2D(1,1);
        Vector2D b =  v.add(a);
        assertEquals(2, b.getX());
        assertEquals(2, b.getY());

        v = new Vector2D(-1,1);
        a = new Vector2D(1,-1);
        b = v.add(a);

        assertEquals(0, b.getX());
        assertEquals(0, b.getY());
    }

    @Test
    public void vectorSubtraction(){
        Vector2D v = new Vector2D(5,7);
        Vector2D a = new Vector2D(14,9);
        Vector2D b =  v.subtract(a);
        assertEquals(-9, b.getX());
        assertEquals(-2, b.getY());
    }

    @Test
    public void scalaMultiplication(){
        Vector2D v = new Vector2D(10,-10);
        v = v.multiplyScalar(2);
        assertEquals(20, v.getX());
        assertEquals(-20, v.getY());
    }

}
