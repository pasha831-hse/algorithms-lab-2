package org.example.solution;

import org.example.model.Point;
import org.example.model.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class CompressedMapSolution extends Solution {
    private int[][] solutionMap;
    private List<Integer> compressedX;
    private List<Integer> compressedY;

    public CompressedMapSolution(List<Rectangle> rectangles) {
        super(rectangles);
        compressCoordinates();
        inflateSolutionMap();
    }

    private void compressCoordinates() {
        compressedX = new ArrayList<>();
        compressedY = new ArrayList<>();

        for (Rectangle rectangle : rectangles) {
            compressedX.addAll(List.of(
                    rectangle.leftDownPoint.x,
                    rectangle.rightUpPoint.x,
                    rectangle.rightUpPoint.x + 1
            ));
            compressedY.addAll(List.of(
                    rectangle.leftDownPoint.y,
                    rectangle.rightUpPoint.y,
                    rectangle.rightUpPoint.y + 1
            ));
        }

        // Create and fill compressed arrays with TreeSets of coordinates,
        // which are already distinct and sorted in natural (ascending) order
        compressedX = new ArrayList<>(new TreeSet<>(compressedX));
        compressedY = new ArrayList<>(new TreeSet<>(compressedY));
    }

    protected int compressPoint(List<Integer> axis, int target) {
        int left = 0;
        int right = axis.size() - 1;
        while (left < right) {
            int middle = (left + right + 1) / 2;
            if (target >= axis.get(middle)) {
                left = middle;
            } else {
                right = middle - 1;
            }
        }
        return left;
    }

    private void inflateSolutionMap() {
        solutionMap = new int[compressedX.size()][compressedY.size()];

        for (Rectangle rectangle : rectangles) {
            // "cmp" means compressed
            Point cmpLeftDownPoint = new Point(
                    compressPoint(compressedX, rectangle.leftDownPoint.x),
                    compressPoint(compressedY, rectangle.leftDownPoint.y)
            );
            Point cmpRightUpPoint = new Point(
                    compressPoint(compressedX, rectangle.rightUpPoint.x),
                    compressPoint(compressedY, rectangle.rightUpPoint.y)
            );

            for (int i = cmpLeftDownPoint.x; i <= cmpRightUpPoint.x; i++) {
                for (int j = cmpLeftDownPoint.y; j <= cmpRightUpPoint.y; j++) {
                    ++solutionMap[i][j];
                }
            }
        }
    }

    @Override
    public int solvePoint(Point point) {
        if (point.x < compressedX.get(0) || point.y < compressedY.get(0)) {
            return 0;
        }

        Point compressedPoint = new Point(
                compressPoint(compressedX, point.x),
                compressPoint(compressedY, point.y)
        );

        if (compressedPoint.x == compressedX.size() || compressedPoint.y == compressedY.size()) {
            return 0;
        }

        return solutionMap[compressedPoint.x][compressedPoint.y];
    }
}
