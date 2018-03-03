package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.interfaces.GenericExpression;
import ru.itmo.ctddev.glamozda.interfaces.Type;

public class Multiply<T extends Number> extends Operations<T> {
    public Multiply(GenericExpression<T> x, GenericExpression<T> y) {
        super(x, y);
    }

    public Type<T> evaluate(Type<T> x, Type<T> y) {
        return x.multiply(y);
    }
}
