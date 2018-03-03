package ru.itmo.ctddev.glamozda;

import ru.itmo.ctddev.glamozda.operations.*;
import ru.itmo.ctddev.glamozda.interfaces.AllExpression;
import ru.itmo.ctddev.glamozda.interfaces.Parser;

import java.util.Arrays;

public class ExpressionParser implements Parser {
    private Tokenizer tk;

    public AllExpression parse(String expression) {
        tk = new Tokenizer(expression);
        tk.parse();
        tk.nextToken();
        return leftRightShift();
    }

    private AllExpression leftRightShift() {
        AllExpression cur = bitOr();
        while (tk.curToken.type == TokenType.LShift || tk.curToken.type == TokenType.RShift) {
            TokenType a = tk.curToken.type;
            tk.nextToken();
            switch (a) {
                case LShift:
                    cur = new LeftShift(cur, bitOr());
                    break;
                case RShift:
                    cur = new RightShift(cur, bitOr());
                    break;
            }
        }
        return cur;
    }

    private AllExpression bitOr() {
        AllExpression cur = bitXor();
        while (tk.curToken.type == TokenType.Bor) {
            tk.nextToken();
            cur = new BitOr(cur, bitXor());
            break;
        }
        return cur;
    }


    private AllExpression bitXor() {
        AllExpression cur = bitAnd();
        while (tk.curToken.type == TokenType.Bxor) {
            tk.nextToken();
            cur = new BitXor(cur, bitAnd());
            break;
        }
        return cur;
    }

    private AllExpression bitAnd() {
        AllExpression cur = addSubtract();
        while (tk.curToken.type == TokenType.Bor) {
            tk.nextToken();
            cur = new BitAnd(cur, addSubtract());
            break;
        }
        return cur;
    }

    private AllExpression addSubtract() {
        AllExpression cur = multiplyDivide();
        while (tk.curToken.type == TokenType.Add || tk.curToken.type == TokenType.Sub) {
            TokenType a = tk.curToken.type;
            tk.nextToken();
            switch (a) {
                case Add:
                    cur = new Add(cur, multiplyDivide());
                    break;
                case Sub:
                    cur = new Subtract(cur, multiplyDivide());
                    break;
            }
        }
        return cur;
    }

    private AllExpression multiplyDivide() {
        AllExpression cur = other();
        while (tk.curToken.type == TokenType.Mul || tk.curToken.type == TokenType.Div) {
            TokenType a = tk.curToken.type;
            tk.nextToken();
            switch (a) {
                case Mul:
                    cur = new Multiply(cur, other());
                    break;
                case Div:
                    cur = new Divide(cur, other());
                    break;
            }
        }
        return cur;
    }

    private AllExpression other() {
        AllExpression cur;

        switch (tk.curToken.type) {
            case Num:
                cur = new Const(tk.curToken.value);
                tk.nextToken();
                return cur;

            case Var:
                cur = new Variable(tk.curToken.variable);
                tk.nextToken();
                return cur;

            case Abs:
                tk.nextToken();
                cur = new Abs(other());
                return cur;

            case Square:
                tk.nextToken();
                cur = new Square(other());
                return cur;

            case Sub:
                tk.nextToken();
                return new Multiply(new Const(-1), other());

            case Left:
                tk.nextToken();
                cur = leftRightShift();
                tk.nextToken();
                return cur;

            default:
                return null;
        }
    }

    private enum TokenType {
        Num, Add, Sub, Mul, Div, Left, Right, Var, End, Abs, Square, LShift, RShift, Bor, Band, Bxor;
    }

    private class Token {
        private int value;
        private TokenType type;
        private String variable;

        public Token(TokenType type) {
            this.value = 0;
            this.type = type;
        }

        public Token(TokenType type, String variable) {
            this.type = type;
            this.variable = variable;
        }

        public Token(TokenType type, int value) {
            this.value = value;
            this.type = type;
        }
    }

    private class Tokenizer {
        private Token[] tokens;
        private Token curToken;
        private int lastChar = 0;
        private int lastToken = 0;
        private final String string;

        public Tokenizer(String string) {
            this.string = string;
        }

        public void nextToken() {
            switch (tokens[lastToken].type) {
                case End:
                    curToken = tokens[lastToken];
                default:
                    curToken = tokens[lastToken++];
            }
        }

        public Tokenizer parse() {
            tokens = new Token[8];
            int i = 0;
            do {
                if (i == tokens.length) {
                    tokens = Arrays.copyOf(tokens, tokens.length * 2);
                }
                tokens[i++] = next();
            } while (tokens[i - 1].type != TokenType.End);

            return this;
        }

        private Token next() {
            char c;
            isWhitespace();
            if (lastChar < string.length()) {
                c = string.charAt(lastChar++);
            } else {
                return new Token(TokenType.End);
            }

            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                try {
                    do {
                        sb.append(c);
                        c = string.charAt(lastChar++);
                    } while (Character.isDigit(c));
                } catch (StringIndexOutOfBoundsException x) {}
                lastChar--;
                int num = Integer.parseUnsignedInt(sb.toString());
                return new Token(TokenType.Num, num);

            } else {
                switch (c) {
                    case '+':
                        return new Token(TokenType.Add);
                    case '-':
                        return new Token(TokenType.Sub);
                    case '*':
                        return new Token(TokenType.Mul);
                    case '/':
                        return new Token(TokenType.Div);
                    case '<':
                        lastChar++;
                        return new Token(TokenType.LShift);
                    case '>':
                        lastChar++;
                        return new Token(TokenType.RShift);
                    case '|':
                        return new Token(TokenType.Bor);
                    case '&':
                        return new Token(TokenType.Band);
                    case '^':
                        return new Token(TokenType.Bxor);
                    case '(':
                        return new Token(TokenType.Left);
                    case ')':
                        return new Token(TokenType.Right);
                    case 'a':
                        lastChar += 2;
                        return new Token(TokenType.Abs);
                    case 's':
                        lastChar += 5;
                        return new Token(TokenType.Square);
                    default:
                        StringBuilder sb = new StringBuilder();
                        try {
                            do {
                                sb.append(c);
                                c = string.charAt(lastChar++);
                            } while (Character.isLetter(c));
                        } catch (StringIndexOutOfBoundsException x) {}
                        lastChar--;
                        return new Token(TokenType.Var, sb.toString());
                }
            }
        }

        private void isWhitespace() {
            while (lastChar < string.length() && Character.isWhitespace(string.charAt(lastChar))) {
                lastChar++;
            }
        }

        private boolean checkString() {
            switch (string.charAt(lastChar - 1)) {
                case 'a':
                    if (lastChar + 2 < string.length() && string.substring(lastChar, lastChar + 2).equals("bs")) {
                        lastChar += 2;
                        return true;
                    }
                    return false;
                case 's':
                    if (lastChar + 5 < string.length() && string.substring(lastChar, lastChar + 5).equals("quare")) {
                        lastChar += 5;
                        return true;
                    }
                    return false;
                default:
                    return false;
            }
        }
    }
}
