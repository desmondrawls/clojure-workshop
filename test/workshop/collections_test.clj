(ns workshop.collections-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [workshop.collections :refer :all]))

(def game
  {:round 2
   :players #{{:name "Una" :ranking 43}
              {:name "Bob" :ranking 77}
              {:name "Cid" :ranking 33}}
   :scores {"Una" 1400
            "Bob" 1240
            "Cid" 1024}})

(fact "adding a player starts with score 0"
  ((add-player {"Una" 1400, "Bob" 1240, "Cid" 1024} "Mel") "Mel") => 0)

(fact "next-round increments the game round"
  (:round (next-round game)) => 3)

(fact "add-score bumps the players score by a specified amount"
  (get-in (add-score game "Cid" 100) [:scores "Cid"]) => 1124)

(fact "find player returns name and ranking"
  (let [Una (find-player game "Una")]
    (:name Una) => "Una"
    (:ranking Una) => 43))