package ru.itmo.ctddev.Glamozda;

public class Subtract extends Operations {

    public Subtract(AllExpression x, AllExpression y) {
        super(x, y);
    }

    public int evaluate(int x, int y) {
        return x - y;
    }

    public double evaluate(double x, double y) {
        return x - y;
    }
}
