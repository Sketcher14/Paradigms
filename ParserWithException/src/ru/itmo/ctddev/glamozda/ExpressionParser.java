package ru.itmo.ctddev.glamozda;

import ru.itmo.ctddev.glamozda.operations.*;
import ru.itmo.ctddev.glamozda.interfaces.TripleExpression;
import ru.itmo.ctddev.glamozda.interfaces.Parser;
import ru.itmo.ctddev.glamozda.exceptions.*;

import java.util.Arrays;

public class ExpressionParser implements Parser {
    private Tokenizer tk;

    public TripleExpression parse(String expression) throws ParserException {
        tk = new Tokenizer(expression);
        tk.parsed();
        tk.nextToken();
        return start(false);
    }

    private TripleExpression start(boolean isLeftBracketPresent) throws ParserException {
        TripleExpression cur = addSubtract();
        switch (tk.curToken.type) {
            case Left:
                throw new SyntaxErrorException("unexpected \"(\" found");

            case Right:
                if (!isLeftBracketPresent)
                    throw new SyntaxErrorException("no opening parenthesis");
                break;

            case End:
                break;
        }
        return cur;
    }

    private TripleExpression addSubtract() throws ParserException {
        TripleExpression cur = multiplyDivide();
        while (tk.curToken.type == TokenType.Add || tk.curToken.type == TokenType.Sub) {
            TokenType a = tk.curToken.type;
            tk.nextToken();
            switch (a) {
                case Add:
                    cur = new CheckedAdd(cur, multiplyDivide());
                    break;
                case Sub:
                    cur = new CheckedSubtract(cur, multiplyDivide());
                    break;
            }
        }
        return cur;
    }

    private TripleExpression multiplyDivide() throws ParserException {
        TripleExpression cur = other();
        while (tk.curToken.type == TokenType.Mul || tk.curToken.type == TokenType.Div) {
            TokenType a = tk.curToken.type;
            tk.nextToken();
            switch (a) {
                case Mul:
                    cur = new CheckedMultiply(cur, other());
                    break;
                case Div:
                    cur = new CheckedDivide(cur, other());
                    break;
            }
        }
        return cur;
    }

    private TripleExpression other() throws ParserException {
        TripleExpression cur;

        switch (tk.curToken.type) {
            case Num:
                cur = new Const(tk.curToken.value);
                tk.nextToken();
                return cur;

            case Var:
                cur = new Variable(tk.curToken.gerVariableName());
                tk.nextToken();
                return cur;

            case Abs:
                tk.nextToken();
                cur = new CheckedAbs(other());
                return cur;

            case Sqrt:
                tk.nextToken();
                cur = new CheckedSqrt(other());
                return cur;

            case Sub:
                tk.nextToken();
                return new CheckedNegate(other());

            case Left:
                tk.nextToken();
                cur = start(true);
                if (tk.curToken.type == TokenType.Right) {
                    tk.nextToken();
                } else {
                    throw new SyntaxErrorException("no closing parenthesis");
                }
                return cur;

            default:
                throw new SyntaxErrorException("number, variable, unary \"-\" or \"(\" expected, found " + tk.curToken.type.toStr());
        }
    }

    private enum TokenType {
        Num, Add, Sub, Mul, Div, Left, Right, Var, End, Abs, Sqrt;

        public String toStr() {
            switch (this) {
                case Add:
                    return "\"+\"";
                case Sub:
                    return "\"-\"";
                case Mul:
                    return "\"*\"";
                case Div:
                    return "\"/\"";
                case Left:
                    return "\"(\"";
                case Right:
                    return "\")\"";
                case Num:
                    return "number";
                case Var:
                    return "variable";
                case Abs:
                    return "\"abs\"";
                case Sqrt:
                    return "\"sqrt\"";
                case End:
                    return "end of string";
                default:
                    return null;
            }

        }
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
        private Token[] tokens;
        private Token curToken;
        private int lastChar = 0;
        private int lastToken = 0;
        private final String string;

        public Tokenizer(String string) {
            this.string = string;
        }

        public void nextToken() throws ParserException {
            switch (tokens[lastToken].type) {
                case End:
                    curToken = tokens[lastToken];
                case Num:
                    if (tokens[lastToken].value == Integer.MIN_VALUE) {
                        throw new InvalidNumber("2147483648");
                    }
                    curToken = tokens[lastToken++];
                    break;

                case Sub:
                    if (tokens[lastToken + 1].type == TokenType.Num && tokens[lastToken + 1].value == Integer.MIN_VALUE) {
                        curToken = tokens[lastToken + 1];
                        lastToken = lastToken + 2;
                    } else {
                        curToken = tokens[lastToken++];
                    }
                    break;

                default:
                    curToken = tokens[lastToken++];
            }
        }

        public Tokenizer parsed() throws ParserException {
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

        private Token next() throws ParserException {
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
                int num;
                String numberString = sb.toString();
                if (numberString.equals("2147483648")) {
                    num = Integer.MIN_VALUE;
                } else {
                    try {
                        num = Integer.parseInt(numberString);
                    } catch (NumberFormatException x) {
                        throw new InvalidNumber(numberString);
                    }
                }
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
                        if (checkString()) {
                            return new Token(TokenType.Abs);
                        }
                    case 's':
                        if (checkString()) {
                            return new Token(TokenType.Sqrt);
                        }

                    default:
                        throw new UnknownSymbolException(string.charAt(lastChar - 1), lastChar - 1);
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
        }
    }
}
