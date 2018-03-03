package ru.itmo.ctddev.glamozda.interfaces;

public interface GenericExpression<T extends Number> {
    Type<T> evaluate(Type<T> x, Type<T> y, Type<T> z);
}
