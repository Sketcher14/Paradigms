package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.exceptions.ParserException;
import ru.itmo.ctddev.glamozda.interfaces.TripleExpression;
import ru.itmo.ctddev.glamozda.exceptions.OverflowException;

public class CheckedMultiply extends CheckedOperations{
    public CheckedMultiply(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    protected int evaluate(int x, int y) throws ParserException {
        if (y > 0) {
            if (x > Integer.MAX_VALUE / y || x < Integer.MIN_VALUE / y) {
                throw new OverflowException(Integer.toString(x) + " * " + Integer.toString(y));
            }
        } else if (y < -1) {
            if (x > Integer.MIN_VALUE / y || x < Integer.MAX_VALUE / y) {
                throw new OverflowException(Integer.toString(x) + " * " + Integer.toString(y));
            }
        } else if (y == -1 && x == Integer.MIN_VALUE) {
            throw new OverflowException(Integer.toString(x) + " * " + Integer.toString(y));
        }
        return x * y;
    }
}
