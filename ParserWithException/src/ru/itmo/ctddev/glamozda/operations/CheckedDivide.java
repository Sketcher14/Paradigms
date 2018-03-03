package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.exceptions.ParserException;
import ru.itmo.ctddev.glamozda.interfaces.TripleExpression;
import ru.itmo.ctddev.glamozda.exceptions.OverflowException;
import ru.itmo.ctddev.glamozda.exceptions.DivisionByZeroException;

public class CheckedDivide extends CheckedOperations{
    public CheckedDivide(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    public int evaluate(int x, int y) throws ParserException {
        if (y == 0) {
            throw new DivisionByZeroException(Integer.toString(x) + " / 0");
        }
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException(Integer.toString(x) + " / " + Integer.toString(y));
        }

        return x / y;
    }
}
