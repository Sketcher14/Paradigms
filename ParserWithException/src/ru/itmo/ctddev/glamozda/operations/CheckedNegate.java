package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.exceptions.ParserException;
import ru.itmo.ctddev.glamozda.exceptions.OverflowException;
import ru.itmo.ctddev.glamozda.interfaces.TripleExpression;

public class CheckedNegate extends CheckedOperations{
    public CheckedNegate(TripleExpression x) {
        super(x, new Const(0));
    }

    protected int evaluate(int x, int y) throws ParserException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("- " + Integer.toString(x));
        }
        return -x;
    }
}
