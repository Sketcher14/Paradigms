package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.exceptions.ParserException;
import ru.itmo.ctddev.glamozda.interfaces.TripleExpression;

public abstract class CheckedOperations implements TripleExpression{
    private TripleExpression a, b;

    protected CheckedOperations(TripleExpression a, TripleExpression b) {
        this.a = a;
        this.b = b;
    }

    protected abstract int evaluate(int x, int y) throws ParserException;

    public int evaluate(int x, int y, int z) throws ParserException{
        return evaluate(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }
}
