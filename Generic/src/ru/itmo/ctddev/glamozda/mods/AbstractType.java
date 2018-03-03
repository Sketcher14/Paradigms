package ru.itmo.ctddev.glamozda.mods;

import ru.itmo.ctddev.glamozda.interfaces.Type;

public abstract class AbstractType<T extends Number> implements Type<T> {
    T value;
    protected AbstractType(Type<T> value) {
        this.value = value.getValue();
    }

    protected AbstractType(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

}
