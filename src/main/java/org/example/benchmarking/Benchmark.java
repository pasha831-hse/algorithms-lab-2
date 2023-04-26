package org.example.benchmarking;

import org.example.model.Point;
import org.example.model.Rectangle;
import org.example.solution.BruteForceSolution;
import org.example.solution.CompressedMapSolution;
import org.example.solution.LPSTSolution;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Benchmark {
    public static PrintWriter writer;
    public static String filePath = "./artefacts/measurement.txt";

    static {
        try {
            writer = new PrintWriter(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    public static void testSolutions(
            BruteForceSolution bruteForceSolution,
            CompressedMapSolution compressedMapSolution,
            LPSTSolution lpstSolution,
            int numberOfRandP
    ) {
        List<Rectangle> rectangles = RectPointGenerator.generateRecommendedRectangles(numberOfRandP);
        List<Point> points = RectPointGenerator.generateRecommendedPoints(numberOfRandP);

        long timeToPrepareBruteForce = Benchmark.benchmarkPreparation(bruteForceSolution, rectangles);
        long timeToPrepareCompressedMap = Benchmark.benchmarkPreparation(compressedMapSolution, rectangles);
        long timeToPrepareLPST = Benchmark.benchmarkPreparation(lpstSolution, rectangles);

        writer.println("PrepareN:" + numberOfRandP);
        writer.println("BruteForce:" + timeToPrepareBruteForce);
        writer.println("CompressedMap:" + timeToPrepareCompressedMap);
        writer.println("LPST:" + timeToPrepareLPST);
        writer.println();

        long bruteForceDuration = Benchmark.benchmark(bruteForceSolution, rectangles, points);
        long compressedMapDuration = Benchmark.benchmark(compressedMapSolution, rectangles, points);
        long LPSTDuration = Benchmark.benchmark(lpstSolution, rectangles, points);

        writer.println("RunN:" + numberOfRandP);
        writer.println("BruteForce:" + bruteForceDuration);
        writer.println("CompressedMap:" + compressedMapDuration);
        writer.println("LPST:" + LPSTDuration);
        writer.println();
    }

    public static void runAllTests(
            BruteForceSolution bruteForceSolution,
            CompressedMapSolution compressedMapSolution,
            LPSTSolution lpstSolution
    ) {
        int[] numberOfRandP = new int[] {
                10, 20, 50, 80, 100, 150, 250, 300, 400, 450,
                500, 600, 700, 800, 900, 1000, 1300, 1500, 2000
        };

        for (int number : numberOfRandP) {
            testSolutions(bruteForceSolution, compressedMapSolution, lpstSolution, number);
            System.gc();
        }

        writer.close();
    }
}
