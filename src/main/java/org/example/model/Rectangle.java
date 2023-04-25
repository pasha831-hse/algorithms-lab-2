package org.example.model;

public class Rectangle {
    public Point leftDownPoint;
    public Point rightUpPoint;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.leftDownPoint = new Point(x1, y1);
        this.rightUpPoint = new Point(x2, y2);
    }
}
