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
}
