package org.example.benchmarking;

import org.example.model.Point;
import org.example.model.Rectangle;
import org.example.solution.BruteForceSolution;
import org.example.solution.CompressedMapSolution;
import org.example.solution.LPSTSolution;

import java.util.List;

public class Benchmark {
    public static long benchmarkPreparation(
            BruteForceSolution bruteForceSolution,
            List<Rectangle> rectangles
    ) {
        long startBenchmark = System.nanoTime();
        bruteForceSolution.prepare(rectangles);
        long endBenchmark = System.nanoTime();

        return endBenchmark - startBenchmark;
    }

    public static long benchmarkPreparation(
            CompressedMapSolution compressedMapSolution,
            List<Rectangle> rectangles
    ) {
        long startBenchmark = System.nanoTime();
        compressedMapSolution.prepare(rectangles);
        long endBenchmark = System.nanoTime();

        return endBenchmark - startBenchmark;
    }

    public static long benchmarkPreparation(
            LPSTSolution lpstSolution,
            List<Rectangle> rectangles
    ) {
        long startBenchmark = System.nanoTime();
        lpstSolution.prepare(rectangles);
        long endBenchmark = System.nanoTime();

        return endBenchmark - startBenchmark;
    }

    public static long benchmark(
        BruteForceSolution bruteForceSolution,
        List<Rectangle> rectangles,
        List<Point> points
    ) {
        bruteForceSolution.prepare(rectangles);

        long startBenchmark = System.nanoTime();
        for (Point point : points) {
            bruteForceSolution.solvePoint(point);
        }
        long endBenchmark = System.nanoTime();

        return endBenchmark - startBenchmark;
    }

    public static long benchmark(
            CompressedMapSolution compressedMapSolution,
            List<Rectangle> rectangles,
            List<Point> points
    ) {
        compressedMapSolution.prepare(rectangles);

        long startBenchmark = System.nanoTime();
        for (Point point : points) {
            compressedMapSolution.solvePoint(point);
        }
        long endBenchmark = System.nanoTime();

        return endBenchmark - startBenchmark;
    }

    public static long benchmark(
            LPSTSolution lpstSolution,
            List<Rectangle> rectangles,
            List<Point> points
    ) {
        lpstSolution.prepare(rectangles);

        long startBenchmark = System.nanoTime();
        for (Point point : points) {
            lpstSolution.solvePoint(point);
        }
        long endBenchmark = System.nanoTime();

        return endBenchmark - startBenchmark;
    }
}
