package game.util;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void addX(double x) {
        this.x += x;
    }

    public void addY(double y) {
        this.y += y;
    }

    public Vector2D addVector(Vector2D movement){
        return new Vector2D(this.x + movement.getX(), this.y + movement.getY());
    }

    public Vector2D multiplyScalar(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    @Override
    public String toString() {
        return "<" + x + ", " + y + ">";
    }
}