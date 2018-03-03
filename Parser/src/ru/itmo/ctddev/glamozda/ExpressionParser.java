package ru.itmo.ctddev.glamozda;

import ru.itmo.ctddev.glamozda.interfaces.AllExpression;
import ru.itmo.ctddev.glamozda.interfaces.Parser;
import ru.itmo.ctddev.glamozda.interfaces.TripleExpression;
import ru.itmo.ctddev.glamozda.operations.*;

public class ExpressionParser implements Parser {
    private String s;
    private int ind;

    public TripleExpression parse(String expression) {
        ind = 0;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            if (!Character.isWhitespace(expression.charAt(i))) {
                str.append(expression.charAt(i));
            }
        }
        s = str.toString();
        return bitOr();
    }

    private AllExpression bitOr() {
        AllExpression cur = bitXor();
        while (s.length() > ind) {
            if (s.charAt(ind) != '|') {
                break;
            }
            ind++;
            cur = new BitOr(cur, bitXor());
        }
        return cur;
    }

    private AllExpression bitXor() {
        AllExpression cur = bitAnd();
        while (s.length() > ind) {
            if (s.charAt(ind) != '^') {
                break;
            }
            ind++;
            cur = new BitXor(cur, bitAnd());
        }
        return cur;
    }

    private AllExpression bitAnd() {
        AllExpression cur = addSubtract();
        while (s.length() > ind) {
            if (s.charAt(ind) != '&') {
                break;
            }
            ind++;
            cur = new BitAnd(cur, addSubtract());
        }
        return cur;
    }

    private AllExpression addSubtract() {
        AllExpression cur = multiplyDivide();
        while (s.length() > ind) {
            if (s.charAt(ind) != '+' && s.charAt(ind) != '-') {
                break;
            }
            ind++;
            if (s.charAt(ind - 1) == '+') {
                cur = new Add(cur, multiplyDivide());
            } else {
                cur = new Subtract(cur, multiplyDivide());
            }
        }
        return cur;
    }


    private AllExpression multiplyDivide() {
        AllExpression cur = unaryMinus();
        while (s.length() > ind) {
            if (s.charAt(ind) != '*' && s.charAt(ind) != '/') {
                break;
            }
            ind++;
            if (s.charAt(ind - 1) == '*') {
                cur = new Multiply(cur, unaryMinus());
            } else {
                cur = new Divide(cur, unaryMinus());
            }
        }
        return cur;
    }

    private AllExpression unaryMinus() {
        if (s.charAt(ind) == '-') {
            ind++;
            return new Multiply(new Const(-1), unaryMinus());
        } else {
            return brackets();
        }
    }

    private AllExpression brackets() {
        char a = s.charAt(ind);
        if (a == '(') {
            ind++;
            AllExpression inBrackets = bitOr();
            ind++;
            return inBrackets;
        }
        return variable();
    }

    private AllExpression variable() {
        if (Character.isLetter(s.charAt(ind))) {
            int i = 1;
            while (ind + i < s.length() && Character.isLetter(s.charAt(ind + i))) {
                i++;
            }
            String a = s.substring(ind, i + ind);
            ind += i;
            return new Variable(a);
        }
        return number();
    }

    private AllExpression number() {
        int i = 0;
        while (ind + i < s.length() && Character.isDigit(s.charAt(ind + i))) {
            i++;
        }
        int num = Integer.parseUnsignedInt(s.substring(ind, i + ind));
        ind += i;
        return new Const(num);
    }
}
