package org.example;

import org.example.model.Point;
import org.example.model.Rectangle;
import org.example.solution.CompressedMapSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Get rectangles from stdin and save them inside rectangles list
        Scanner scanner = new Scanner(System.in);

        int numberOfRectangles = scanner.nextInt();
        List<Rectangle> rectangles = new ArrayList<>();

        for (int i = 0; i < numberOfRectangles; i++) {
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();

            rectangles.add(new Rectangle(x1, y1, x2, y2));
        }

        // Create result list and
        // get points from stdin and save them inside points list
        List<Point> points = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        int numberOfPoints = scanner.nextInt();
        for (int i = 0; i < numberOfPoints; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            Point point = new Point(x, y);
            points.add(point);
        }

        // Create solution class
        // and save answers inside result array

//        BruteForceSolution solution = new BruteForceSolution(rectangles);

        CompressedMapSolution solution = new CompressedMapSolution(rectangles);
        for (Point point : points) {
            result.add(solution.solvePoint(point));
        }

        System.out.println(Arrays.toString(result.toArray()).replace("[", "").replace("]", "").replace(",", ""));
    }
}