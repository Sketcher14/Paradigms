package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.interfaces.AllExpression;

public class Square extends Operations{
    public Square(AllExpression x) {
        super(x, new Const(0));
    }

    protected int evaluate(int x, int y) {
        return x * x;
    }

    protected double evaluate(double x, double y) {
        return x * x;
    }
}
