package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.interfaces.AllExpression;

public class RightShift extends Operations{
    public RightShift(AllExpression x, AllExpression y) {
        super(x, y);
    }

    protected int evaluate(int x, int y) {
        return x >> y;
    }

    protected double evaluate(double x, double y) {
        int p = (int) x >> (int) y;
        return p;
    }
}
