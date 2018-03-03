package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.interfaces.GenericExpression;
import ru.itmo.ctddev.glamozda.interfaces.Type;

public class Subtract<T extends Number> extends Operations<T> {

    public Subtract(GenericExpression<T> x, GenericExpression<T> y) {
        super(x, y);
    }

    protected Type<T> evaluate(Type<T> x, Type<T> y) {
        return x.subtract(y);
    }

}
