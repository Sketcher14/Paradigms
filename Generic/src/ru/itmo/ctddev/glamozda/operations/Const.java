package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.interfaces.GenericExpression;
import ru.itmo.ctddev.glamozda.interfaces.Type;

public class Const<T extends Number> implements GenericExpression<T> {
    Type<T> value;

    public Const(Type<T> value) {
        this.value = value;
    }

    public Type<T> evaluate(Type<T> x, Type<T> y, Type<T> z) {
        return value;
    }
}
