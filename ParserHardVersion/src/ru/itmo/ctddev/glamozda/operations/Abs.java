package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.interfaces.AllExpression;

public class Abs extends Operations{
    public Abs(AllExpression x) {
        super(x, new Const(0));
    }

    protected int evaluate(int x, int y) {
        if (x < 0) {
            return -x;
        } else {
            return x;
        }
    }

    protected double evaluate(double x, double y) {
        if (x < 0) {
            return -x;
        } else {
            return x;
        }
    }



}
