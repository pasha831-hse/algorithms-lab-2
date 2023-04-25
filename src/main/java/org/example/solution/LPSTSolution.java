package org.example.solution;

import org.example.model.Node;
import org.example.model.Point;
import org.example.model.Rectangle;
import org.example.model.Side;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

// LSPT = Lazy Persistent Segment Tree
public class LPSTSolution {
    public List<Rectangle> rectangles;

    protected List<Integer> compressedX;
    protected List<Integer> compressedY;

    List<Node> roots;
    List<Integer> compressedRootsX;

    public LPSTSolution(List<Rectangle> rectangles) {
        this.rectangles = rectangles;

        compressCoordinates();

        roots = new ArrayList<>();
        compressedRootsX = new ArrayList<>();

        inflateLSPT();
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

    private int compressPoint(List<Integer> axis, int target) {
        int left = 0;
        int right = axis.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (axis.get(mid) > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left - 1;
    }

    public void inflateLSPT() {
        List<Side> sides = new ArrayList<>();

        for (Rectangle rectangle : rectangles) {
            sides.add(new Side(
                    compressPoint(compressedX, rectangle.leftDownPoint.x),
                    compressPoint(compressedY, rectangle.leftDownPoint.y),
                    compressPoint(compressedY, rectangle.rightUpPoint.y + 1),
                    true
            ));
            sides.add(new Side(
                    compressPoint(compressedX, rectangle.rightUpPoint.x + 1),
                    compressPoint(compressedY, rectangle.leftDownPoint.y),
                    compressPoint(compressedY, rectangle.rightUpPoint.y + 1),
                    false
            ));
        }

        Collections.sort(sides);

        Node root = new Node();
        int previousCompressedX = sides.get(0).compressedX;
        for (Side side : sides) {
            if (side.compressedX != previousCompressedX) {
                roots.add(root);
                compressedRootsX.add(previousCompressedX);
                previousCompressedX = side.compressedX;
            }
            root = root.add(0, compressedY.size(), side.topY, side.bottomY, side.isOpening);
        }

        compressedRootsX.add(previousCompressedX);
        roots.add(root);
    }

    public int solvePoint(Point point) {
        if (point.x < compressedX.get(0) || point.y < compressedY.get(0) ||
                point.x > compressedX.get(compressedX.size() - 1) || point.y > compressedY.get(compressedY.size() - 1)) {
            return 0;
        }

        int compressedXIndex = compressPoint(compressedX, point.x);
        int compressedYIndex = compressPoint(compressedY, point.y);

        return roots.get(compressPoint(compressedRootsX, compressedXIndex))
                .getTotalQuantity(0, compressedY.size(), compressedYIndex);
    }
}
