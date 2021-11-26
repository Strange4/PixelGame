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

    public Vector2D add(double x, double y) {
        return new Vector2D(this.x + x, this.y + y);
    }

    public Vector2D add(Vector2D vector2D){
        return new Vector2D(vector2D.getX() + this.x, vector2D.getY() + this.y);
    }

    /**
     * subtracts the vector given to the current vector and returns a new vector
     * @param vector2D the vector to be subtracted from the current vector
     * @return a new vector that is the subtraction the current vector minus the given vector
     */
    public Vector2D subtract(Vector2D vector2D){
        return new Vector2D(this.x - vector2D.getX(), this.y - vector2D.getY());
    }

    public Vector2D multiplyScalar(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    @Override
    public String toString() {
        return "<" + x + ", " + y + ">";
    }
}
