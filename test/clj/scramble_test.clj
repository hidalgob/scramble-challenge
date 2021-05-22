(ns scramble-test
  (:require [clojure.test :refer [deftest is testing]]
            [scramble :as scramble]))

(deftest scramble-tests
  (testing "scramble works on given tests"
    (is (true? (scramble/scramble? "rekqodlw" "world")))
    (is (true? (scramble/scramble? "cedewaraaossoqqyt" "codewars")))
    (is (false? (scramble/scramble? "katas" "steak")))))
