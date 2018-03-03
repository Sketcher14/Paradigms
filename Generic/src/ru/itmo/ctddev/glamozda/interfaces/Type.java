package ru.itmo.ctddev.glamozda.interfaces;

public interface Type<T extends Number> {
    T getValue();
    Type<T> parse(String str);
    Type<T> add(Type<T> y);
    Type<T> subtract(Type<T> y);
    Type<T> multiply(Type<T> y);
    Type<T> divide(Type<T> y);
    Type<T> negate();
    Type<T> mod(Type<T> y);
    Type<T> abs();
    Type<T> square();
}
