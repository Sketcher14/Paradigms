package ru.itmo.ctddev.Glamozda;

public class Variable implements AllExpression {
    private final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    public int evaluate(int x) {
        return x;
    }

    public double evaluate(double x) {
        return x;
    }

}
