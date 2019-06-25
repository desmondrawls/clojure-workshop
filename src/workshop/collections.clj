(ns workshop.collections
  (:require [clojure.spec.alpha :as s]))

(defn add-player
  [current-players name]
  (assoc current-players name 0))

(defn next-round
  [game]
  (update-in game [:round] inc))

(defn add-score
  [game player-name points]
  (update-in game [:scores player-name] + points))

(defn incStr
  [x]
  (->> x
    (str ".")
    (str "hello")))

(defn find-player
  [game player-name]
  (->> game
    :players
    (filter #(-> % :name (= player-name)))
    first))

(defn find-player
  [game player-name]
  (let [players (:players game)
        matches-name #(= (:name %) player-name)]
    (some #(when (matches-name %) %) players)))

(def email-regex #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$")
(s/def ::email-type (s/and string? #(re-matches email-regex %)))

(s/def ::acctid int?)
(s/def ::first-name string?)
(s/def ::last-name string?)
(s/def ::email ::email-type)

(s/def ::person (s/keys :req [::first-name ::last-name ::email]
                  :opt [::phone]))

(defrecord Person [first-name last-name email phone])




