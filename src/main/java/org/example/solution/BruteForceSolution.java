package org.example.solution;

import org.example.model.Point;
import org.example.model.Rectangle;

import java.util.List;

public class BruteForceSolution extends Solution {
    public BruteForceSolution(List<Rectangle> rectangles) {
        super(rectangles);
    }

    @Override
    public int solvePoint(Point point) {
        int answer = 0;

        for (Rectangle rectangle : rectangles) {
            if (point.x >= rectangle.leftDownPoint.x && point.x <= rectangle.rightUpPoint.x &&
                    point.y >= rectangle.leftDownPoint.y && point.y <= rectangle.rightUpPoint.y) {
                ++answer;
            }
        }

        return answer;
    }
}
