package ru.itmo.ctddev.glamozda.mods;

import java.math.BigInteger;
import ru.itmo.ctddev.glamozda.interfaces.Type;

public class GenericBigInteger extends AbstractType<BigInteger> {
    /*
    public GenericBigInteger(Type<BigInteger> value) {
        super(value);
    }
*/
    public GenericBigInteger(BigInteger value) {
        super(value);
    }

    public Type<BigInteger> add(Type<BigInteger> y) {
        return new GenericBigInteger(getValue().add(y.getValue()));
    }

    public Type<BigInteger> subtract(Type<BigInteger> y) {
        return new GenericBigInteger(getValue().subtract(y.getValue()));
    }

    public Type<BigInteger> multiply(Type<BigInteger> y) {
        return new GenericBigInteger(getValue().multiply(y.getValue()));
    }

    public Type<BigInteger> divide(Type<BigInteger> y) {
        return new GenericBigInteger(getValue().divide(y.getValue()));
    }

    public Type<BigInteger> parse(String str) {
        return new GenericBigInteger(new BigInteger(str));
    }

    public Type<BigInteger> negate() {
        return new GenericBigInteger(getValue().negate());
    }

    public Type<BigInteger> abs() {
        return new GenericBigInteger(getValue().abs());
    }

    public Type<BigInteger> mod(Type<BigInteger> y) {
        return new GenericBigInteger(BigInteger.valueOf
                (new GenericInt(getValue().intValue()).mod(new GenericInt(y.getValue().intValue())).getValue())
        );
    }

    public Type<BigInteger> square() {
        return new GenericBigInteger(getValue().multiply(getValue()));
    }
}
