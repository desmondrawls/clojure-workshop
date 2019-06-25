(ns workshop.factorial)

(defn factorial
  [n]
  (loop [total 1 step n]
    (if (= step 1)
      total
      (recur (* total step) (- step 1)))))

(defn my-contains?
  [xs x]
  (loop [xs xs x x]
    (if (= (first xs) x)
      true
      (if (= (count xs) 1)
        false
        (recur (rest xs) x)))))

(defn my-contains-all?
  [xs ys]
  (loop [xs xs ys ys]
    (if (empty? ys)
      true
      (if (my-contains? xs (first ys))
        (recur xs (rest ys))
        false))))

(defn guess [secret]
  (println "I am thinking of a number from 1 to 10")
  (println "Enter your guess:")
  (loop [guess (read)]
    (if (= guess secret)
      (println "Correct. You win!")
      (if (> guess secret)
        (do (println "Too high. Guess again:")
          (recur (read)))
        (do (println "Too low. Guess again:")
          (recur (read)))))))

(defn make-round [{:keys [:secret :guess]}]
  (cond
    (not (number? guess)) {:continue false :outcome :invalid}
    (= guess secret) {:continue false, :outcome :win}
    (> guess secret) {:continue true, :outcome :too-high}
    :else  {:continue true, :outcome :too-low}))

(defn outcome->message [outcome]
  (outcome {:invalid "Sorry that is an invalid entry. Game over."
            :win "Correct. You win!"
            :too-low "Too low. Guess again:"
            :too-high "Too high. Guess again:"}))

(def introduction-message
  "I am thinking of a number from 1 to 10\nEnter your guess:")

(defn guess-testable [secret]
  (println introduction-message)
  (loop [guess (read)]
    (let [round (make-round {:secret secret :guess guess})]
      (println (outcome->message (:outcome round)))
      (when (:continue round) (recur (read))))))

(defn name-of
  [user]
  user)

(defn -main [& args]
  (guess-testable 42))
