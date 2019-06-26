(ns workshop.macros)

(defmacro my-when [test & body]
  (list 'if test (cons 'do body)))


(defmacro bar [& body]
  `(let [x# 4]
     ~@body))

(defmacro my-or
  ([] nil)
  ([x] x)
  ([x & next]
  `(let [or# ~x]
     (if or# or# (or ~@next)))))

(defmacro just-do-it [& expressions]
  `(do ~@expressions))

(defmacro execute [expression]
  `(do (clojure.pprint/pprint '~expression)
       (try ~expression
            (catch Exception e#
                   (clojure.repl/pst e#)))
       (println "done")))


(defmacro transcript [& expressions]
  `(loop [remaining# ~expressions]
     (when (not (empty? remaining#))
       (execute (first remaining#))
       (recur (rest remaining#)))))