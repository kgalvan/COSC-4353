package fibonacci;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
                                             
public interface FibonacciSolutionTest {

    FibonacciSolution createInstance();

    @Test
    default void fibonacciForPositionsUpto10() {
        FibonacciSolution fibonacciSolution = createInstance();
        assertAll(
                () -> assertEquals(BigInteger.valueOf(1), fibonacciSolution.compute(0)),
                () -> assertEquals(BigInteger.valueOf(1), fibonacciSolution.compute(1)),
                () -> assertEquals(BigInteger.valueOf(2), fibonacciSolution.compute(2)),
                () -> assertEquals(BigInteger.valueOf(3), fibonacciSolution.compute(3)),
                () -> assertEquals(BigInteger.valueOf(5), fibonacciSolution.compute(4)),
                () -> assertEquals(BigInteger.valueOf(8), fibonacciSolution.compute(5)),
                () -> assertEquals(BigInteger.valueOf(13), fibonacciSolution.compute(6)),
                () -> assertEquals(BigInteger.valueOf(21), fibonacciSolution.compute(7)),
                () -> assertEquals(BigInteger.valueOf(34), fibonacciSolution.compute(8)),
                () -> assertEquals(BigInteger.valueOf(55), fibonacciSolution.compute(9)),
                () -> assertEquals(BigInteger.valueOf(89), fibonacciSolution.compute(10))
        );
    }
}
