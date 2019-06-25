(ns workshop.core-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [workshop.core :refer :all]))

(deftest fahrenheit->celsius6_test
  (testing "fahrenheit->celsius with style"
    (is (= (fahrenheit->celsius6 32) 0))))

(fact "`fahrenheit->celsius6` converts with style"
  (fahrenheit->celsius6 32) => 0)

(tabular
  (fact "Converting Fahrenheit to Celsius"
    (fahrenheit->celsius6 ?fahrenheit) => ?celcius)
  ?fahrenheit ?celcius
  32          0
  0           -160/9
  100         340/9)

(fact "`greeting` says hello"
  (greet :hello) => "Hello world.")