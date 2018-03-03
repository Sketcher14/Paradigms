package ru.itmo.ctddev.glamozda;

import ru.itmo.ctddev.glamozda.mods.*;
import ru.itmo.ctddev.glamozda.interfaces.*;

import java.math.BigInteger;

public class GenericTabulator implements Tabulator {
    private String string;
    private int x1, x2, y1, y2, z1, z2;

    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        this.string = expression;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.z1 = z1;
        this.z2 = z2;

        switch (mode) {
            case "i":
                return solve(new GenericExpressionParser<>(new GenericInt(0)));
            case "d":
                return solve(new GenericExpressionParser<>(new GenericDouble(0.0)));
            case "bi":
                return solve(new GenericExpressionParser<>(new GenericBigInteger(new BigInteger("0"))));
            case "u":
                return solve(new GenericExpressionParser<>(new GenericUnsignInt(0)));
            case "f":
                return solve(new GenericExpressionParser<>(new GenericFloat(0.f)));
            case "b":
                return solve(new GenericExpressionParser<>(new GenericByte((byte) 0)));
            default:
                return null;
        }
    }

    private <T extends Number> Object[][][] solve(GenericExpressionParser<T> parser) {
        GenericExpression<T> cur = parser.parse(string);
        Object[][][] ans = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int k = 0; k <= z2 - z1; k++) {
                    Type<T> x = parser.getType().parse(Integer.toString(x1 + i));
                    Type<T> y = parser.getType().parse(Integer.toString(y1 + j));
                    Type<T> z = parser.getType().parse(Integer.toString(z1 + k));
                    try {
                        ans[i][j][k] = cur.evaluate(x, y, z).getValue();
                    } catch (ArithmeticException ex) {
                        ans[i][j][k] = null;
                    }
                }
            }
        }
        return ans;
    }
}
