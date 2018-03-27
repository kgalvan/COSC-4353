package fibonacci;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

public class FibonacciMemoizedRecursiveSolution extends FibonacciRecursiveSolution {
    private Map<Integer, BigInteger> fibonacciMemo = new LinkedHashMap<>();

    public BigInteger compute(int position) {
        if (fibonacciMemo.containsKey(position))
            return fibonacciMemo.get(position);

        BigInteger result = super.compute(position);
        fibonacciMemo.put(position, result);
        return fibonacciMemo.get(position);
    }
}
