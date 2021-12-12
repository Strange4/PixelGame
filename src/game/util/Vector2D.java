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

    /**
     * Returns a new vector representing the addition of the current vector and the given one
     * @param vector2D Vector2D vector to be added to current vector
     * @return Vector2D resulting vector of addition of current and given vector
     */
    public Vector2D add(Vector2D vector2D){
        return new Vector2D(vector2D.getX() + this.x, vector2D.getY() + this.y);
    }

    /**
     * Returns a new vector representing the result of the multiplication of the given scalar and current vector
     * @param scalar double scalar
     * @return Vector2D vector representing the result of the multiplication of current vector by given scalar
     */
    public Vector2D multiplyScalar(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    @Override
    public String toString() {
        return "<" + x + ", " + y + ">";
    }
}
