package ru.itmo.ctddev.glamozda;

import ru.itmo.ctddev.glamozda.exceptions.ParserException;

public class Main {
    public static void main(String[] args) throws ParserException{
        ExpressionParser bbb = new ExpressionParser();
        String str = "absx 10";
        System.out.println(bbb.parse(str).evaluate(0,0,0));
    }
}
