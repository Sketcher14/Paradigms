package ru.itmo.ctddev.glamozda.mods;

import ru.itmo.ctddev.glamozda.interfaces.Type;

public class GenericUnsignInt extends AbstractType<Integer> {
    public GenericUnsignInt(Type<Integer> value) {
        super(value);
    }

    public GenericUnsignInt(Integer value) {
        super(value);
    }
    
    public Type<Integer> add(Type<Integer> y) {
        return new GenericUnsignInt(getValue() + y.getValue());
    }
    
    public Type<Integer> subtract(Type<Integer> y) {
        return new GenericUnsignInt(getValue() - y.getValue());
    }
    
    public Type<Integer> multiply(Type<Integer> y) {
        return new GenericUnsignInt(getValue() * y.getValue());
    }
    
    public Type<Integer> divide(Type<Integer> y) {
        return new GenericUnsignInt(getValue() / y.getValue());
    }

    public Type<Integer> parse(String str) {
        return new GenericUnsignInt(Integer.parseInt(str));
    }
    public Type<Integer> mod(Type<Integer> y) {
        return new GenericUnsignInt(getValue() % y.getValue());
    }
    
    public Type<Integer> negate() {
        return new GenericUnsignInt(-getValue());
    }
    
    public Type<Integer> abs() {
        int x = getValue();
        if (x > 0) {
            return new GenericUnsignInt(x);
        } else {
            return new GenericUnsignInt(-x);
        }
    }

    public Type<Integer> square() {
        return new GenericInt(getValue() * getValue());
    }
}
