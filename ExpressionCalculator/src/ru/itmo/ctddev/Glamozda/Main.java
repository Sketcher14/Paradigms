package ru.itmo.ctddev.Glamozda;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Add(new Const(5),new Variable("x")).evaluate(5));
    }
}
