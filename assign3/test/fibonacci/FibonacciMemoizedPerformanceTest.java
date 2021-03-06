package fibonacci;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FibonacciMemoizedPerformanceTest {

    private long computeTime25(FibonacciSolution fibSolution){
        long startTime = System.nanoTime();
        fibSolution.compute(30);
        return System.nanoTime() - startTime;
    }

    @Test
    void compareRecursionWithMemoized25(){
        long recursiveComputationTime = computeTime25(new FibonacciRecursiveSolution());
        long memoizedComputationTime = computeTime25(new FibonacciMemoizedRecursiveSolution());

        assertTrue(memoizedComputationTime * 10 < recursiveComputationTime);
    }

}
