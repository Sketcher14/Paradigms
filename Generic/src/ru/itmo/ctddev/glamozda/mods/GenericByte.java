package ru.itmo.ctddev.glamozda.mods;

import ru.itmo.ctddev.glamozda.interfaces.Type;

public class GenericByte extends AbstractType<Byte> {
    public GenericByte(Type<Byte> value) {
        super(value);
    }

    public GenericByte(Byte value) {
        super(value);
    }

    public Type<Byte> add(Type<Byte> y) {
        return new GenericByte((byte) (getValue() + y.getValue()));
    }

    public Type<Byte> subtract(Type<Byte> y) {
        return new GenericByte((byte) (getValue() - y.getValue()));
    }

    public Type<Byte> multiply(Type<Byte> y) {
        return new GenericByte((byte) (getValue() * y.getValue()));
    }

    public Type<Byte> divide(Type<Byte> y) {
        return new GenericByte((byte) (getValue() / y.getValue()));
    }

    public Type<Byte> parse(String str) {
        return new GenericByte(Byte.parseByte(str));
    }

    public Type<Byte> mod(Type<Byte> y) {
        return new GenericByte((byte) (getValue() % y.getValue()));
    }

    public Type<Byte> negate() {
        return new GenericByte((byte) -getValue());
    }

    public Type<Byte> abs() {
        byte x = getValue();
        if (x > 0) {
            return new GenericByte(x);
        } else {
            return new GenericByte((byte) -x);
        }
    }

    public Type<Byte> square() {
        return new GenericByte((byte) (getValue() * getValue()));
    }
}
