package ru.itmo.ctddev.glamozda.operations;

import ru.itmo.ctddev.glamozda.interfaces.GenericExpression;
import ru.itmo.ctddev.glamozda.interfaces.Type;

public class Variable<T extends Number> implements GenericExpression<T> {
    String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    public Type<T> evaluate(Type<T> x, Type<T> y, Type<T> z) {
        switch (variable.charAt(0)) {
            case 'x':
                return x;
            case 'y':
                return y;
            case 'z':
                return z;
            default: return null;
        }
    }
}
