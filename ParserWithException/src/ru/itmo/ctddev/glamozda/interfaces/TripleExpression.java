package ru.itmo.ctddev.glamozda.interfaces;

import ru.itmo.ctddev.glamozda.exceptions.ParserException;

public interface TripleExpression {
    int evaluate(int x, int y, int z) throws ParserException;
}
