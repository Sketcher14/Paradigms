package ru.itmo.ctddev.Glamozda;

public class Const implements AllExpression {
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
}
