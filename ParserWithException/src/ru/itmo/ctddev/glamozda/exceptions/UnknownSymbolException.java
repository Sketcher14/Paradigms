package ru.itmo.ctddev.glamozda.exceptions;

public class UnknownSymbolException extends ParserException{
    public UnknownSymbolException(char c, int i) {
        super("Unknown character: \"" + c + "\", position: " + i);
    }
}
