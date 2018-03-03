package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.interfaces.GenericExpression;
import ru.itmo.ctddev.glamozda.interfaces.Type;

public class Square<T extends Number> extends Operations<T> {
    public Square (GenericExpression<T> x) {
        super(x, x);
    }

    protected Type<T> evaluate(Type<T> x, Type<T> y) {
        return x.square();
    }
}
