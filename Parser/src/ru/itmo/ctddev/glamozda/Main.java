package ru.itmo.ctddev.glamozda;

public class Main {
    public static void main(String[] args) {
        ExpressionParser bbb = new ExpressionParser();
        String str = "10";
        System.out.println(bbb.parse(str).evaluate(0,0,0));
    }
}
