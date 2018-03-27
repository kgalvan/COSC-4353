package fibonacci;

import java.math.BigInteger;

public class FibonacciRecursiveSolution implements FibonacciSolution{

    public BigInteger compute(int position){
        if(position < 2)
            return new BigInteger("1");

       return compute(position - 1).add(compute(position - 2));

    }
}

