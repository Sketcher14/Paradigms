package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.exceptions.ParserException;
import ru.itmo.ctddev.glamozda.interfaces.TripleExpression;
import ru.itmo.ctddev.glamozda.exceptions.OverflowException;

public class CheckedAbs extends CheckedOperations{
    public CheckedAbs(TripleExpression x) {
        super(x, new Const(0));
    }

    protected int evaluate(int x, int y) throws ParserException{
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException(Integer.toString(x));
        }
        if (x < 0) {
            return -x;
        } else {
            return x;
        }
    }


}
