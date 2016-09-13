;
;  (C) Copyright 2016  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns hurvinek.core-test
  (:require [clojure.test :refer :all]
            [hurvinek.core :refer :all]))

;
; Common functions used by tests.
;

(defn callable?
    "Test if given function-name is bound to the real function."
    [function-name]
    (clojure.test/function? function-name))

;
; Tests for various function definitions
;

(deftest test-start-server-existence
    "Check that the hurvinek.core/start-server definition exists."
    (testing "if the hurvinek.core/start-server definition exists."
        (is (callable? 'hurvinek.core/start-server))))


(deftest test-get-and-check-port-existence
    "Check that the hurvinek.core/get-and-check-port definition exists."
    (testing "if the hurvinek.core/get-and-check-port definition exists."
        (is (callable? 'hurvinek.core/get-and-check-port))))


(deftest test-get-port-existence
    "Check that the hurvinek.core/get-port definition exists."
    (testing "if the hurvinek.core/get-port definition exists."
        (is (callable? 'hurvinek.core/get-port))))


(deftest test--main-existence
    "Check that the hurvinek.core/-main definition exists."
    (testing "if the hurvinek.core/-main definition exists."
        (is (callable? 'hurvinek.core/-main))))

;
; Tests for function behaviours
;

(deftest test-get-port
    "Check the function hurvinek.core/get-port."
    (testing "the function hurvinek.core/get-port."
        (is (= (get-port "1")     "1"))
        (is (= (get-port "2")     "2"))
        (is (= (get-port "3000")  "3000"))
        (is (= (get-port "65534") "65534"))
        (is (= (get-port "65535") "65535"))))

(deftest test-get-port-special-cases
    "Check the function hurvinek.core/get-port."
    (testing "the function hurvinek.core/get-port."
        (is (= (get-port nil)     "3000"))
        (is (= (get-port "")      "3000"))
        (is (= (get-port 0)       "3000"))
        (is (= (get-port 1)       "3000"))
        (is (= (get-port 65535)   "3000"))
        (is (= (get-port 65536)   "3000"))))

(deftest test-get-port-negative
    "Check the function hurvinek.core/get-port."
    (testing "the function hurvinek.core/get-port."
        (is (thrown? AssertionError (get-port "0")))
        (is (thrown? AssertionError (get-port "-1")))
        (is (thrown? AssertionError (get-port "-2")))
        (is (thrown? AssertionError (get-port "65536")))
        (is (thrown? AssertionError (get-port "65537")))
        (is (thrown? AssertionError (get-port "1000000")))))

(deftest test-get-and-check-port
    "Check the function hurvinek.core/get-and-check-port."
    (testing "the function hurvinek.core/get-and-check-port."
        (is (= (get-and-check-port "1")     "1"))
        (is (= (get-and-check-port "2")     "2"))
        (is (= (get-and-check-port "65534") "65534"))
        (is (= (get-and-check-port "65535") "65535"))))

(deftest test-get-and-check-port-negative
    "Check the function hurvinek.core/get-and-check-port."
    (testing "the function hurvinek.core/get-and-check-port."
        (is (thrown? AssertionError (get-and-check-port "-1")))
        (is (thrown? AssertionError (get-and-check-port "0")))
        (is (thrown? AssertionError (get-and-check-port "65536")))
        (is (thrown? AssertionError (get-and-check-port "65537")))
        (is (thrown? AssertionError (get-and-check-port "1000000")))))

