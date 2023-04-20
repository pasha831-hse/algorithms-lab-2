package org.example;

import org.example.model.Point;
import org.example.model.Rectangle;
import org.example.solution.CompressedMapSolution;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class CompressedMapTest {
    @Test
    void testBruteForceSolution() {
        List<Rectangle> rectangles = List.of(
                new Rectangle(2, 2, 6, 8),
                new Rectangle(5, 4, 9, 10),
                new Rectangle(4, 0, 11, 6),
                new Rectangle(8, 2, 12, 12)
        );

        List<Point> points = List.of(
                new Point(2, 2),
                new Point(12, 12),
                new Point(10, 4),
                new Point(5, 5),
                new Point(2, 10)
        );

        List<Integer> result = new ArrayList<>();
        CompressedMapSolution solution = new CompressedMapSolution(rectangles);
        for (Point point : points) {
            result.add(solution.solvePoint(point));
        }

        List<Integer> expected = List.of(1, 1, 2, 3, 0);
        assertIterableEquals(expected, result);
    }
}
