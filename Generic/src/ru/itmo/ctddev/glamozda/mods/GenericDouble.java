package ru.itmo.ctddev.glamozda.mods;

import ru.itmo.ctddev.glamozda.interfaces.Type;

public class GenericDouble extends AbstractType<Double> {
    /*
    public GenericDouble(Type<Double> value) {
        super(value);
    }
*/
    public GenericDouble(Double value) {
        super(value);
    }

    public Type<Double> add(Type<Double> y) {
        return new GenericDouble(getValue() + y.getValue());
    }

    public Type<Double> subtract(Type<Double> y) {
        return new GenericDouble(getValue() - y.getValue());
    }

    public Type<Double> multiply(Type<Double> y) {
        return new GenericDouble(getValue() * y.getValue());
    }

    public Type<Double> divide(Type<Double> y) {
        return new GenericDouble(getValue() / y.getValue());
    }

    public Type<Double> parse(String str) {
        return new GenericDouble(Double.parseDouble(str));
    }

    public Type<Double> negate() {
        return new GenericDouble(-getValue());
    }

    public Type<Double> abs() {
        return new GenericDouble(Math.abs(getValue()));
    }

    public Type<Double> mod(Type<Double> y) {
        return new GenericDouble(getValue() % y.getValue());
    }

    public Type<Double> square() {
        return new GenericDouble(getValue() * getValue());
    }
}
