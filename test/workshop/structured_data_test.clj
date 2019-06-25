(ns workshop.structured-data-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [workshop.structured-data :refer :all]))


(def courses
  {"CS-101" (->Course "CS-101" "Comp Sci Concepts" nil 2)
   "CS-110" (->Course "CS-110" "Data Structures" #{"CS-101"} 3)
   "CS-120" (->Course "CS-120" "Intro to Algorithms" #{"CS-101"} 3)
   "CS-220" (->Course "CS-220" "Compilers" #{"CS-110" "CS-120"} 4)})

(def faculty
  {138138 (->Faculty 138138 "John" "Stringbean")
   293843 (->Faculty 293843 "Maya" "Mayfair")
   234232 (->Faculty 234232 "Emily" "Surcher")})

(def offerings
  {230203 (->Offering 230203 "CS-101" 138138 ["Mo","We","Fr"] "8:00am" "8:50am")
   234109 (->Offering 234109 "CS-110" 293843 ["Mo","We","Fr"] "10:00am" "10:50am")
   934934 (->Offering 934934 "CS-120" 138138 ["Tu", "Th"] "1:00pm" "3:00pm")})

(def catalog
  {:courses courses
   :faculty faculty
   :offerings offerings})

(deftest courses
  (testing "works"
    (let [with-course (add-course catalog (->Course "CS-999" "Clojure trivia" nil 99))]
      (is (= 99 (get-in with-course [:courses "CS-999" :credits]))))))

(defprotocol Switchable
  (on [this])
  (off [this]))

(deftype Light []
  Switchable
  (on [_] (+ 0 1))
  (off [_] (- 1 1)))

(deftype Fan []
  Switchable
  (on [_] "on")
  (off [_] "off"))

(deftest polymorphism
  (testing "Fan"
    (is (= "on" (on (->Fan))))
    (is (= "off" (off (->Fan)))))
  (testing "Light"
    (is (= 1 (on (->Light))))
    (is (= 0 (off (->Light))))))