package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.interfaces.GenericExpression;
import ru.itmo.ctddev.glamozda.interfaces.Type;

public class Mod<T extends Number> extends Operations<T> {
    public Mod (GenericExpression<T> x, GenericExpression<T> y) {
        super(x, y);
    }

    protected Type<T> evaluate(Type<T> x, Type<T> y) {
        return x.mod(y);
    }
}
