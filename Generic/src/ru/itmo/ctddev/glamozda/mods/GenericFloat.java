package ru.itmo.ctddev.glamozda.mods;

import ru.itmo.ctddev.glamozda.interfaces.Type;

public class GenericFloat extends AbstractType<Float> {
    public GenericFloat(Type<Float> value) {
        super(value);
    }

    public GenericFloat(Float value) {
        super(value);
    }

    public Type<Float> add(Type<Float> y) {
        return new GenericFloat(getValue() + y.getValue());
    }

    public Type<Float> subtract(Type<Float> y) {
        return new GenericFloat(getValue() - y.getValue());
    }

    public Type<Float> multiply(Type<Float> y) {
        return new GenericFloat(getValue() * y.getValue());
    }

    public Type<Float> divide(Type<Float> y) {
        return new GenericFloat(getValue() / y.getValue());
    }

    public Type<Float> parse(String str) {
        return new GenericFloat(Float.parseFloat(str));
    }

    public Type<Float> negate() {
        return new GenericFloat(-getValue());
    }

    public Type<Float> abs() {
        return new GenericFloat(Math.abs(getValue()));
    }

    public Type<Float> mod(Type<Float> y) {
        return new GenericFloat(getValue() % y.getValue());
    }

    public Type<Float> square() {
        return new GenericFloat(getValue() * getValue());
    }
}
