(ns scramble-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as str]
            [scramble :as scramble]))

(deftest scramble-test
  (testing "should work on given examples"
    (is (true? (scramble/scramble? "rekqodlw" "world")))
    (is (true? (scramble/scramble? "cedewaraaossoqqyt" "codewars")))
    (is (false? (scramble/scramble? "katas" "steak"))))

  (testing "should return false if pool is smaller than sample"
    (is (false? (scramble/scramble? "a" "aa")))
    (is (false? (scramble/scramble? "abc" "abcd"))))

  (testing "should return false if both strings are empty"
    (is (false? (scramble/scramble? "" "")))))

(deftest valid-values-test
  (testing "should return true on lower-case values"
    (is (true? (scramble/valid-values? "rekqodlw" "world")))
    (is (true? (scramble/valid-values? "cedewaraaossoqqyt" "codewars"))))

  (testing "should return false on upper-case values"
    (is (false? (scramble/valid-values? "KATAS" "STEAK")))
    (is (false? (scramble/valid-values? "Kat" "kaT"))))

  (testing "should return false on numbers values"
    (is (false? (scramble/valid-values? "123456" "123")))
    (is (false? (scramble/valid-values? "ka1t" "k2at"))))

  (testing "should return false on symbols values"
    (is (false? (scramble/valid-values? "(*&$@!" ")*%#&#*")))
    (is (false? (scramble/valid-values? "kat%" "ka&t"))))

  (testing "should return false if any string is empty"
    (is (false? (scramble/valid-values? "a" "")))
    (is (false? (scramble/valid-values? "" "a")))
    (is (false? (scramble/valid-values? "" ""))))

  (testing "should return false if any string is bigger than api limit"
    (is (false? (scramble/valid-values? (str/join (repeat 101 "a")) "a")))
    (is (false? (scramble/valid-values? "a" (str/join (repeat 101 "a")))))))
