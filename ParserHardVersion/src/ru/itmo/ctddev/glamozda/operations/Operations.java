package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.interfaces.AllExpression;

public abstract class Operations implements AllExpression {
    private AllExpression a, b;

    protected Operations(AllExpression a, AllExpression b) {
        this.a = a;
        this.b = b;
    }

    protected abstract int evaluate(int x, int y);

    public int evaluate(int x) {
        return evaluate(a.evaluate(x),b.evaluate(x));
    }

    protected abstract double evaluate(double x, double y);

    public double evaluate(double x) {
        return evaluate(a.evaluate(x), b.evaluate(x));
    }


    public int evaluate(int x, int y, int z) {
        return evaluate(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }
}
