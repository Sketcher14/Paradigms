package ru.itmo.ctddev.glamozda.mods;

import ru.itmo.ctddev.glamozda.interfaces.Type;

public class GenericInt extends AbstractType<Integer> {
    /*
    public GenericInt(Type<Integer> value) {
        super(value);
    }
    */
    public GenericInt(Integer value) {
        super(value);
    }

    public Type<Integer> add(Type<Integer> y) {
        return new GenericInt(Math.addExact(getValue(), y.getValue()));
    }

    public Type<Integer> subtract(Type<Integer> y) {
        return new GenericInt(Math.subtractExact(getValue(), y.getValue()));
    }

    public Type<Integer> multiply(Type<Integer> y) {
        return new GenericInt(Math.multiplyExact(getValue(), y.getValue()));
    }

    public Type<Integer> divide(Type<Integer> y) {
        return new GenericInt(getValue() / y.getValue());
    }

    public Type<Integer> parse(String str) {
        return new GenericInt(Integer.parseInt(str));
    }

    public Type<Integer> negate() {
        return new GenericInt(Math.negateExact(getValue()));
    }

    public Type<Integer> abs() {
        return new GenericInt(Math.abs(getValue()));
    }

    public Type<Integer> mod(Type<Integer> y) {
        return new GenericInt(getValue() % y.getValue());
    }

    public Type<Integer> square() {
        return new GenericInt(Math.multiplyExact(getValue(), getValue()));
    }
}
