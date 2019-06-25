(ns workshop.factorial-test
  (:require [clojure.test :refer :all]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [clojure.spec.test.alpha :as stest]
            [workshop.factorial :as f]
            [workshop.core :as temp]))

(deftest factorial-test
  (testing "one"
    (is (= (f/factorial 1) 1)))
  (testing "five"
    (is (= (f/factorial 5) 120))))

(s/fdef f/factorial
  :args (s/cat :input number?)
  :ret number?)

(s/fdef temp/fahrenheit->celsius6
  :args (s/cat :degrees number?)
  :ret number?
  :fn (fn [example]
        (= (temp/celsius->fahrenheit (:ret example))
          :degrees (:args example))))

(s/fdef temp/fizzbuzz-str
  :args (s/cat :input (s/int-in 2 10))
  :ret string?)

(deftest guess-test
  (testing "invalid input"
    (is (=
          {:continue false :outcome :invalid}
          (f/make-round {:secret 4 :guess "four"}))))
  (testing "win"
    (is (=
          {:continue false :outcome :win}
          (f/make-round {:secret 4 :guess 4}))))
  (testing "too low"
    (is (=
          {:continue true :outcome :too-low}
          (f/make-round {:secret 4 :guess 1}))))
  (testing "too high"
    (is (=
          {:continue true :outcome :too-high}
          (f/make-round {:secret 8 :guess 9})))))

(stest/instrument `temp/fizzbuzz2)

(s/def :user/name string?)
(s/def :user/age number?)
(s/def :user/favorite-color #{:blue :green :yellow :red} )
(s/def ::user (s/keys :req [:user/name :user/age] :opt [:user/favorite-color]))

(s/fdef f/name-of
  :args (s/cat :input ::user)
  :ret ::user)

;:fn (fn [{:keys [:ret :args]}]
;      (contains? #{:blue :green :yellow :red} (:user/favorite-color ret) ))



