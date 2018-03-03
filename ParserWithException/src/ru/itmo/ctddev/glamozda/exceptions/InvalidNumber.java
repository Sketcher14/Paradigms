package ru.itmo.ctddev.glamozda.exceptions;

public class InvalidNumber extends ParserException{
    public InvalidNumber(String str) {
        super("Invalid number: " + str);
    }
}
