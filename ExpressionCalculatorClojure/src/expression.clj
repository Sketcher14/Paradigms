(defn constant [value]
  (fn [args] value))

(defn variable [v]
  (fn [args] (args v)))

(defn operation [f]
  (fn [& ops]
    (fn [args]
      (apply f (map (fn [fun] (fun args)) ops)))))

(def div (fn [a b] (/ (double a) (double b))))
(def sinhCalc (fn [arg] (Math/sinh arg)))
(def coshCalc (fn [arg] (Math/cosh arg)))

(def add (operation +))
(def subtract (operation -))
(def multiply (operation *))
(def divide (operation div))
(def negate (operation -))
(def sinh (operation sinhCalc))
(def cosh (operation coshCalc))

(def op {'+ add, '- subtract, '* multiply, '/ divide, 'negate negate, 'sinh sinh, 'cosh cosh})

(defn parse [m v c]
  (fn [expr]
    ((fn parseList [l]
       (cond
         (symbol? l) (v (str l))
         (number? l) (c l)
         (list? l) (apply (m (first l)) (map parseList (rest l)))))
      (read-string expr))))

(def parseFunction (parse op variable constant))