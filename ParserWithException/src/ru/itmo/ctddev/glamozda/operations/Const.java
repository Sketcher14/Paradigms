package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.interfaces.TripleExpression;

public class Const implements TripleExpression {
    private final double value;

    public Const(double value) {
        this.value = value;
    }

    public int evaluate(int x) {
        return (int) value;
    }

    public double evaluate(double x) {
        return value;
    }

    public int evaluate(int x, int y, int z) {
        return (int) value;
    }
}
