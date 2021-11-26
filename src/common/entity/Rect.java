package common.entity;

import game.util.Vector2D;

import java.util.Vector;

public class Rect {
    private double x;
    private double y;
    private double width;
    private double height;

    public Rect(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rect(Vector2D position, Vector2D dimensions) {
        this.x = position.getX();
        this.y = position.getY();
        this.width = dimensions.getX();
        this.height = dimensions.getY();
    }

    public Rect(Vector2D position, double width, double height) {
        this.x = position.getX();
        this.y = position.getY();
        this.width = width;
        this.height = height;
    }

    public Rect(double x, double y, Vector2D dimension) {
        this.x = x;
        this.y = y;
        this.width = dimension.getX();
        this.height = dimension.getY();
    }

    public Vector2D getPosition() {
        return new Vector2D(this.x, this.y);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
