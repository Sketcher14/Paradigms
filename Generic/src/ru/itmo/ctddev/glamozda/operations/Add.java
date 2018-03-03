package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.interfaces.GenericExpression;
import ru.itmo.ctddev.glamozda.interfaces.Type;

public class Add<T extends Number> extends Operations<T> {
    public Add(GenericExpression<T> x, GenericExpression<T> y) {
        super(x, y);
    }
    protected Type<T> evaluate(Type<T> x, Type<T> y) {
        return x.add(y);
    }

}
