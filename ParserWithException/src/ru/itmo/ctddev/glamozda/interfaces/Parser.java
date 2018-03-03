package ru.itmo.ctddev.glamozda.interfaces;

import ru.itmo.ctddev.glamozda.exceptions.ParserException;
import ru.itmo.ctddev.glamozda.interfaces.TripleExpression;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser {
    TripleExpression parse(String expression) throws ParserException;
}
