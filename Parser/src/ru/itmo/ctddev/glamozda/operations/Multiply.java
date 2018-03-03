package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.interfaces.AllExpression;

public class Multiply extends Operations {

    public Multiply(AllExpression x, AllExpression y) {
        super(x, y);
    }

    public int evaluate(int x, int y) {
        return x * y;
    }

    public double evaluate(double x, double y) {
        return x * y;
    }
}
