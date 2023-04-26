package org.example;

import org.example.benchmarking.Benchmark;
import org.example.solution.BruteForceSolution;
import org.example.solution.CompressedMapSolution;
import org.example.solution.LPSTSolution;

public class Main {
    public static void main(String[] args) {
        Benchmark.runAllTests(
                new BruteForceSolution(),
                new CompressedMapSolution(),
                new LPSTSolution()
        );
    }
}