package org.example.solution;

import org.example.model.Point;
import org.example.model.Rectangle;

import java.util.List;

public abstract class Solution {
    protected List<Rectangle> rectangles;

    public Solution(List<Rectangle> rectangles) {
        this.rectangles = rectangles;
    }

    public abstract int solvePoint(Point point);

    public int rightBinSearch(List<Integer> axis, int target) {
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
}
