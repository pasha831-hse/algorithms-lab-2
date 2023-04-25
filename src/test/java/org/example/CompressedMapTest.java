package org.example;

import org.example.model.Point;
import org.example.model.Rectangle;
import org.example.solution.CompressedMapSolution;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class CompressedMapTest {
    static List<Rectangle> rectangles1;
    static List<Rectangle> rectangles2;

    static List<Point> points1;
    static List<Point> points2;

    @BeforeAll
    static void inflateRectanglesAndPoints() {
        rectangles1 = List.of(
                new Rectangle(2, 2, 6, 8),
                new Rectangle(5, 4, 9, 10),
                new Rectangle(4, 0, 11, 6),
                new Rectangle(8, 2, 12, 12)
        );
        points1 = List.of(
                new Point(2, 2),
                new Point(12, 12),
                new Point(10, 4),
                new Point(5, 5),
                new Point(2, 10)
        );

        rectangles2 = List.of(
                new Rectangle(0, 0, 10, 10),
                new Rectangle(0, 0, 20, 20),
                new Rectangle(0, 0, 30, 30),
                new Rectangle(0, 0, 40, 30),
                new Rectangle(0, 0, 60, 60)
        );
        points2 = List.of(
                new Point(0, 0),
                new Point(10, 10),
                new Point(30, 30),
                new Point(-1, -1),
                new Point(61, 0),
                new Point(5, 5),
                new Point(15, 5),
                new Point(40, 30),
                new Point(40, 31)
        );
    }

    @Test
    void test1BruteForceSolution() {
        List<Integer> result = new ArrayList<>();
        CompressedMapSolution solution = new CompressedMapSolution(rectangles1);

        for (Point point : points1) {
            result.add(solution.solvePoint(point));
        }

        List<Integer> expected = List.of(1, 1, 2, 3, 0);
        assertIterableEquals(expected, result);
    }

    @Test
    void test2BruteForceSolution() {
        List<Integer> result = new ArrayList<>();
        CompressedMapSolution solution = new CompressedMapSolution(rectangles2);

        for (Point point : points2) {
            result.add(solution.solvePoint(point));
        }

        List<Integer> expected = List.of(5, 5, 3, 0, 0, 5, 4, 2, 1);
        assertIterableEquals(expected, result);
    }
}
