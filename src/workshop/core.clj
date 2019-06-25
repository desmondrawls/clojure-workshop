(ns workshop.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn fahrenheit->celsius2
  "converts"
  [{:keys [degrees]}]
  (/ (* 5 (- degrees 32)) 9))

(defn fahrenheit->celsius4
  "converts with let"
  [{:keys [degrees]}]
  (let [degrees (- degrees 32)
        degrees (* 5 degrees)
        degrees (/ degrees 9)]
    degrees))

(defn fahrenheit->celsius5
  "converts with style"
  [{:keys [degrees]}]
  (let [minus32 #(- % 32)
        times5 (partial * 5)
        divideBy9 #(/ % 9)
        convert (comp divideBy9 times5 minus32)]
    (convert degrees)))

(defn fahrenheit->celsius6
  "converts with style"
  [degrees]
  (let [minus32 #(- % 32)
        times5 (partial * 5)
        divideBy9 #(/ % 9)]
    (-> degrees minus32 times5 divideBy9)))

(defn celsius->fahrenheit
  "converts back"
  [degrees]
  (let [plus32 #(+ % 32)
        divideBy5 #(/ % 5)
        multiplyBy9 (partial * 9)]
    (-> degrees multiplyBy9 divideBy5 plus32 int)))

(defn append
  [new base]
  (str base new))

(defn greet
  "switch on a greeting"
  [x]
  (cond->> "world"
    (= :hello x) (str "Hello ")
    (= :bye x) (str "Goodbye ")
    :always (append ".")))

(defn greet2
  "switch on a greeting"
  [x]
  (cond-> "world"
    :always (str ".")
    (= :hello x) (str "Hello ")
    (= :bye x) (str "Goodbye ")))

(defn divisible-by? [divisor number]
  (zero? (mod number divisor)))

(defn fizzbuzz [n]
  (dotimes [i n]
    (println
      (cond-> nil
        (divisible-by? 3 i) (str "Fizz")
        (divisible-by? 5 i) (str "Buzz")
        :always (or (str i))))))

(defn fizzbuzz-str [n]
  (cond
    (and (divisible-by? 3 n) (divisible-by? 5 n)) "FizzBuzz"
    (divisible-by? 3 n) "Fizz"
    (divisible-by? 5 n) "Buzz"
    :else (str n)))

(defn fizzbuzz2 [n]
  (doseq [i (range 1 (inc n))]
    (println (fizzbuzz-str i))))

(defn has-bob? [s]
  (contains? s "Bob"))

(defn list->map
  [xs]
  (into {} (map (partial into []) (partition 2 xs))))

(defrecord Person [attributes])

(def inc-and-filter (comp (map inc) (filter odd?)))
(def special+ (inc-and-filter +))


