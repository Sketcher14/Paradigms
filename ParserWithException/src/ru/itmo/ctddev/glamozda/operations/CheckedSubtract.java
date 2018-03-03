package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.exceptions.ParserException;
import ru.itmo.ctddev.glamozda.interfaces.TripleExpression;
import ru.itmo.ctddev.glamozda.exceptions.OverflowException;

public class CheckedSubtract extends CheckedOperations{
    public CheckedSubtract(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    protected int evaluate(int x, int y) throws ParserException{
        int r = x - y;
        if (((x ^ y) & (x ^ r)) < 0) {
            throw new OverflowException(Integer.toString(x) + " - " + Integer.toString(y));
        }
        return r;
    }
}
