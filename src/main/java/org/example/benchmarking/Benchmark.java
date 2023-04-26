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
}
