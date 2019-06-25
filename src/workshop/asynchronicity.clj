(ns workshop.asynchronicity
  (:require [clojure.core.async :as async :refer [<! >! >!! <!!]]))


(defn demo1 []
  (let [c1 (async/chan 1)]
    (>!! c1 :a)
    (<!! c1)))

(defn demo2 []
  (let [c1 (async/chan 1)
        g1 (async/go (println :in-the-go
                 (<! c1)))]
    (>!! c1 :a)))

(defn demo3 []
  (let [c1 (async/chan 1)
        g1 (async/go (println
                 :in-the-go
                 (<! c1)))
        g2 (async/go (<! (async/timeout 3000))
               (>! c1 :done))]
    (<!! g2)))

(defn demo5 []
  (let [g1 (go (tap> "Calling Google")
               (<! (async/timeout 300))
               "Google result")
        g2 (go (tap> "Calling Yahoo")
               (<! (async/timeout 1000))
               "Yahoo result")]
    (async/alt!!
      g1 ([x] (println "Got answer from Google: " x))
      g2 ([x] (println "Got answer from Yahoo:" x)))))

(defn demo10 []
  (let [output (chan 10)
        input (chan 1)
        control (async/go-loop [state []]
                  (let [val (<! input)]
                    (if val
                      (do (>! output val)
                          (recur (conj state val)))
                      state)))]
    (>!! input 1)
    (>!! input 2)
    (>!! input 3)
    (async/close! input)
    (println (<!! (async/into [] output)))
    (<!! control)))

