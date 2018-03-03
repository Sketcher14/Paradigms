function operation(f, oper) {
    function Cons() {
        this.ops = Array.prototype.slice.call(arguments, 0);
    }

    Cons.prototype.toString = function () {
        return this.ops.map(function (fun) {
                return fun.toString();
            }).join(" ") + " " + oper;
    };
    Cons.prototype.evaluate = function () {
        var args = arguments;
        return f.apply(null, this.ops.map(function (fun) {
            return fun.evaluate.apply(fun, args);
        }));
    };
    Cons.prototype.prefix = function () {
        return "(" + oper + " " + this.ops.map(function (fun) {
                return fun.prefix();
            }).join(" ") + ")";
    };
    return Cons;
}

var variables = {"x": 0, "y": 1, "z": 2};

function Variable(arg) {
    this.arg = arg;
}
Variable.prototype.toString = function () {
    return this.arg;
};
Variable.prototype.evaluate = function () {
    return arguments[variables[this.arg]];
};
Variable.prototype.prefix = function () {
    return this.arg;
};


function Const(arg) {
    this.cnst = arg;
}
Const.prototype.toString = function () {
    return this.cnst.toString();
};
Const.prototype.evaluate = function () {
    return this.cnst;
};
Const.prototype.prefix = function () {
    return this.cnst.toString();
};


var Add = operation(function (x, y) {
    return x + y;
}, "+");
var Subtract = operation(function (x, y) {
    return x - y;
}, "-");
var Multiply = operation(function (x, y) {
    return x * y;
}, "*");
var Divide = operation(function (x, y) {
    return x / y;
}, "/");

var Negate = operation(function (x) {
    return -x;
}, "negate");

var Sin = operation(function (x) {
    return Math.sin(x);
}, "sin");
var Cos = operation(function (x) {
    return Math.cos(x);
}, "cos");

var ops = {};
ops['+'] = {Op: Add, Args: 2};
ops['-'] = {Op: Subtract, Args: 2};
ops['*'] = {Op: Multiply, Args: 2};
ops['/'] = {Op: Divide, Args: 2};

ops['negate'] = {Op: Negate, Args: 1};
ops['sin'] = {Op: Sin, Args: 1};
ops['cos'] = {Op: Cos, Args: 1};

function ParsePrefixError(msg) {
    this.message = msg;
    this.name = "ParsePrefixError";
}
ParsePrefixError.prototype = Object.create(Error.prototype);

function parsePrefix(expr) {
    var regex = /\s*([\(\)]|\-?[A-Za-z]+|\-?\d+|[-!"#$%&'*+,./:;<=>?@[\\\]_`{|}~]+)/g;
    var lastArg = null;
    var lastIndex = 0;

    if (expr === "") {
        throw new ParsePrefixError("Empty input");
    }

    function expression(leftBracket) {
        var lIndex = regex.lastIndex + 1;
        var s = regex.exec(expr);
        var tt = lastArg;
        if (lastArg === null && s[1] !== "(") {
            lastArg = s[1];
            lastIndex = lIndex;
        }

        if (s === null) {
            throw new ParsePrefixError("Unexpected end of string");
        }
        var arg = s[1];

        if (arg.match(/\-?\d+/)) {
            if (!(tt in ops)) {
                throw new ParsePrefixError("No operation before " + lastArg + " at " + lastIndex);
            }
            return new Const(Number(arg));
        }
        if (variables[arg] >= 0) {
            if (!(tt in ops)) {
                throw new ParsePrefixError("No operation before " + lastArg + " at " + lastIndex);
            }
            return new Variable(arg);
        }
        switch (arg) {
            case "(":
                var result = expression(true);

                s = regex.exec(expr);
                if (s === null || s[1] !== ')') {
                    throw new ParsePrefixError("Too many arguments after " + lastArg + " at " + lastIndex);
                }
                return result;

            case ")":
                if (expr[lIndex - 2] === '(') {
                    throw new ParsePrefixError("Empty op");
                } else {
                    throw new ParsePrefixError("Too few arguments after " + lastArg + " at " + lastIndex);
                }

        }

        if (!ops.hasOwnProperty(arg)) {
            throw new ParsePrefixError("Unknown symbol: " + arg + " at " + lastIndex);
        }

        if (leftBracket) {
            var args = [];

            for (var i = 0; i < ops[arg].Args; i++) {
                args.push(expression(false));
            }

            obj = Object.create(ops[arg].Op.prototype);
            ops[arg].Op.apply(obj, args);
            return obj;
        } else {
            throw new ParsePrefixError("Expression expected, found " + arg + " at " + lastIndex)
        }
    }

    var result = expression(false);

    if (regex.exec(expr) !== null) {
        throw new ParsePrefixError("Excessive info");
    }

    return result;
}

//println(parsePrefix("+"));
