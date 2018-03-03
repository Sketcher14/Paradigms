package ru.itmo.ctddev.glamozda;

import ru.itmo.ctddev.glamozda.mods.*;
import ru.itmo.ctddev.glamozda.interfaces.*;
import java.math.BigInteger;


public class Main {
    public static void main(String[] args)  {
        String string = "x + y mod (z + 1)";
        int x1 = -14, x2 = 14, y1 = -16, y2 = 18, z1 = -1, z2 = 3;
        GenericExpressionParser<Integer> parser = new GenericExpressionParser<>(new GenericInt(0));
        GenericExpression<Integer> cur = parser.parse(string);
        Object[][][] ans = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        int i = 0, j = 0, k = 3;
        Type<Integer> x = parser.getType().parse(Integer.toString(x1 + i));
        Type<Integer> y = parser.getType().parse(Integer.toString(y1 + j));
        Type<Integer> z = parser.getType().parse(Integer.toString(z1 + k));
        ans[i][j][k] = cur.evaluate(x, y, z).getValue();
        System.out.println(ans[i][j][k]);
    }
}


