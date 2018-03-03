package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.interfaces.GenericExpression;
import ru.itmo.ctddev.glamozda.interfaces.Type;

public abstract class Operations<T extends Number> implements GenericExpression<T> {
    private GenericExpression<T> a, b;

    protected Operations(GenericExpression<T> a, GenericExpression<T> b) {
        this.a = a;
        this.b = b;
    }

    protected abstract Type<T> evaluate(Type<T> x, Type<T> y);

    public Type<T> evaluate(Type<T> x, Type<T> y, Type<T> z) {
        return evaluate(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }
}
