package ru.itmo.ctddev.glamozda;

import ru.itmo.ctddev.glamozda.operations.*;
import ru.itmo.ctddev.glamozda.interfaces.GenericExpression;
import ru.itmo.ctddev.glamozda.interfaces.Type;

import java.util.ArrayList;

public class GenericExpressionParser<T extends Number> {
    private Tokenizer tk;
    private Type<T> type;

    public GenericExpressionParser(Type<T> type) {
        this.type = type;
    }

    public Type<T> getType() {
        return type;
    }

    public GenericExpression<T> parse(String expression) {
        tk = new Tokenizer(expression);
        tk.parsed();
        tk.nextToken();
        return addSubtract();
    }

    private GenericExpression<T> addSubtract() {
        GenericExpression<T> cur = multiplyDivideMod();
        while (tk.curToken.type == TokenType.Add || tk.curToken.type == TokenType.Sub) {
            TokenType a = tk.curToken.type;
            tk.nextToken();
            switch (a) {
                case Add:
                    cur = new Add<>(cur, multiplyDivideMod());
                    break;
                case Sub:
                    cur = new Subtract<>(cur, multiplyDivideMod());
                    break;
            }
        }
        return cur;
    }

    private GenericExpression<T> multiplyDivideMod() {
        GenericExpression<T> cur = other();
        while (tk.curToken.type == TokenType.Mul || tk.curToken.type == TokenType.Div || tk.curToken.type == TokenType.Mod) {
            TokenType a = tk.curToken.type;
            tk.nextToken();
            switch (a) {
                case Mul:
                    cur = new Multiply<>(cur, other());
                    break;
                case Div:
                    cur = new Divide<>(cur, other());
                    break;
                case Mod:
                    cur = new Mod<>(cur, other());
                    break;
            }
        }
        return cur;
    }

    private GenericExpression<T> other() {
        GenericExpression<T> cur;

        switch (tk.curToken.type) {
            case Num:
                cur = new Const<>(getType().parse(Integer.toString(tk.curToken.value)));
                tk.nextToken();
                return cur;

            case Var:
                cur = new Variable<>(tk.curToken.gerVariableName());
                tk.nextToken();
                return cur;

            case Abs:
                tk.nextToken();
                cur = new Abs<>(other());
                return cur;

            case Square:
                tk.nextToken();
                cur = new Square<>(other());
                return cur;

            case Sub:
                tk.nextToken();
                return new Negate<>(other());

            case Left:
                tk.nextToken();
                cur = addSubtract();
                tk.nextToken();
                return cur;
            default:
                return null;
        }
    }

    private enum TokenType {
        Num, Add, Sub, Mul, Div, Left, Right, Var, End, Abs, Square, Mod
    }

    private class Token {
        private int value;
        private TokenType type;

        public Token(TokenType type) {
            this.value = 0;
            this.type = type;
        }

        public Token(TokenType type, char variable) {
            this.type = type;
            this.value = (int) variable;
        }

        public Token(TokenType type, int value) {
            this.value = value;
            this.type = type;
        }

        public String gerVariableName() {
            return Character.toString((char) value);
        }
    }

    private class Tokenizer {
        private ArrayList<Token> tokens;
        private Token curToken;
        private int lastChar = 0;
        private int lastToken = 0;
        private final String string;

        public Tokenizer(String string) {
            this.string = string;
        }

        public void nextToken() {
            switch (tokens.get(lastToken).type) {
                case End:
                    curToken = tokens.get(lastToken);
                default:
                    curToken = tokens.get(lastToken++);
            }
        }

        public Tokenizer parsed() {
            tokens = new ArrayList<>();
            int i = 0;
            do {

                tokens.add(next());
            } while (tokens.get(tokens.size() - 1).type != TokenType.End);

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
                } catch (StringIndexOutOfBoundsException x) {
                }
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
                    case '(':
                        return new Token(TokenType.Left);
                    case ')':
                        return new Token(TokenType.Right);
                    case 'x':
                    case 'y':
                    case 'z':
                        return new Token(TokenType.Var, c);
                    case 'a':
                        lastChar += 2;
                        return new Token(TokenType.Abs);
                    case 's':
                        lastChar += 5;
                        return new Token(TokenType.Square);
                    case 'm':
                        lastChar += 2;
                        return new Token(TokenType.Mod);
                    default:
                        return null;
                }
            }
        }

        private void isWhitespace() {
            while (lastChar < string.length() && Character.isWhitespace(string.charAt(lastChar))) {
                lastChar++;
            }
        }
/*
        private boolean checkString() {
            switch (string.charAt(lastChar - 1)) {
                case 'a':
                    if (lastChar + 3 < string.length() && string.substring(lastChar, lastChar + 2).equals("bs")) {
                        if (string.charAt(lastChar + 2) == ' ' || string.charAt(lastChar + 2) == '-' || string.charAt(lastChar + 2) == '(') {
                            lastChar += 2;
                            return true;
                        } else {
                            return false;
                        }
                    }
                    break;
                case 's':
                    if (lastChar + 4 < string.length() && string.substring(lastChar, lastChar + 3).equals("qrt")) {
                        if (string.charAt(lastChar + 3) == ' ' || string.charAt(lastChar + 3) == '-' || string.charAt(lastChar + 3) == '(') {
                            lastChar += 3;
                            return true;
                        } else {
                            return false;
                        }
                    }
                    break;
            }
            return false;
        }*/
    }
}
