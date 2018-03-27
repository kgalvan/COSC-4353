package fibonacci;

import java.math.BigInteger;

public class FibonacciIterativeSolution implements FibonacciSolution{

    public BigInteger compute(int position){
        BigInteger previous = new BigInteger("1");
        BigInteger next = new BigInteger("1");

        for (int i = 0; i < position; i++) {
            BigInteger temp = previous.add(next);
            previous = next;
            next = temp;
        }
        return previous;
    }
}

