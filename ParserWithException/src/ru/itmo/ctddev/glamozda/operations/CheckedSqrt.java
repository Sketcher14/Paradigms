package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.exceptions.ParserException;
import ru.itmo.ctddev.glamozda.interfaces.TripleExpression;
import ru.itmo.ctddev.glamozda.exceptions.SqrtException;

public class CheckedSqrt extends CheckedOperations {
    public CheckedSqrt (TripleExpression x) {
        super(x, new Const(0));
    }

    protected int evaluate(int x, int y) throws ParserException{
        if (x < 0) {
            throw new SqrtException("Can't extract the root " + Integer.toString(x) + " < 0");
        } else {
            return sqrt(x);
        }
    }

    private int sqrt(int x) {
        boolean decreased = false;
        int result = 1, nx;
        for (;;) {
            if (result == 0) {
                break;
            }
            nx = (result + x / result) >> 1;
            if (result == nx || nx > result && decreased) {
                break;
            }
            decreased = nx < result;
            result = nx;
        }
        return result;
    }
}
