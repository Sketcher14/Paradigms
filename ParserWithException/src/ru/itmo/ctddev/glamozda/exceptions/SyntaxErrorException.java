package ru.itmo.ctddev.glamozda.exceptions;

import java.util.StringJoiner;

public class SyntaxErrorException extends ParserException{
    public SyntaxErrorException(String str) {
        super("Syntax error: " + str);
    }

    public static SyntaxErrorException forTokens(String found, String... expected) throws ParserException{
        StringBuilder sb = new StringBuilder();
        StringJoiner sj = new StringJoiner(", ", "", "");

        for (String s : expected) {
            sj.add(s);
        }

        return new SyntaxErrorException(sb.append(sj.toString()).append(" expected, found ").append(found).toString());
    }
}
