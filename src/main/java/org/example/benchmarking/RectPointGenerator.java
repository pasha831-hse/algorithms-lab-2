package org.example.benchmarking;

import org.example.model.Point;
import org.example.model.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class RectPointGenerator {
    public static List<Rectangle> generateRecommendedRectangles(int numberOfRectangles) {
        List<Rectangle> rectangles = new ArrayList<>(numberOfRectangles);

        for (int i = 0; i < numberOfRectangles; i++) {
            rectangles.add(new Rectangle(
                    10 * i,
                    10 * i,
                    10 * (2 * numberOfRectangles - i),
                    10 * (2 * numberOfRectangles - i)
            ));
        }

        return rectangles;
    }

    public static List<Point> generateRecommendedPoints(int numberOfPoints) {
        int primeX = 47;
        int primeY = 53;

        List<Point> points = new ArrayList<>(numberOfPoints);

        for (int i = 0; i < numberOfPoints; i++) {
            points.add(new Point(
                    (int) (Math.pow(primeX * i, 31) % (20 * numberOfPoints)),
                    (int) (Math.pow(primeY * i, 31) % (20 * numberOfPoints))
            ));
        }

        return points;
    }
}
