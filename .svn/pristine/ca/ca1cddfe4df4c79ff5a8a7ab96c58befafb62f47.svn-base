package fibonacci;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;

public class FibonacciIterativeSolutionTest implements FibonacciSolutionTest {

    public FibonacciSolution createInstance() {
        return new FibonacciIterativeSolution();
    }

    @Test
    void canaryTest(){
        assertTrue(true);
    }

    @Test
    void fibIterative50(){
        FibonacciSolution fibonacciIterativeSolution = createInstance();
        assertEquals(new BigInteger("20365011074"), fibonacciIterativeSolution.compute(50));
    }

    @Test
    void simpleIterationForPosition300(){
        FibonacciSolution fibonacciIterativeSolution = createInstance();
        assertEquals(new BigInteger("359579325206583560961765665172189099052367214309267232255589801"), fibonacciIterativeSolution.compute(300));
    }


}