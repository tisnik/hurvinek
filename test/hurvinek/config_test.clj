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

(ns hurvinek.config-test
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

(deftest test-parse-int-existence
    "Check that the hurvinek.config/parse-int definition exists."
    (testing "if the hurvinek.config/parse-int definition exists."
        (is (callable? 'hurvinek.config/parse-int))))


(deftest test-parse-float-existence
    "Check that the hurvinek.config/parse-float definition exists."
    (testing "if the hurvinek.config/parse-float definition exists."
        (is (callable? 'hurvinek.config/parse-float))))


(deftest test-load-configuration-existence
    "Check that the hurvinek.config/load-configuration definition exists."
    (testing "if the hurvinek.config/load-configuration definition exists."
        (is (callable? 'hurvinek.config/load-configuration))))


(deftest test-print-configuration-existence
    "Check that the hurvinek.config/print-configuration definition exists."
    (testing "if the hurvinek.config/print-configuration definition exists."
        (is (callable? 'hurvinek.config/print-configuration))))

;
; Test for function behaviours
;

(deftest test-parse-boolean
    "Check the behaviour of function emender-jenkins.config/parse-boolean."
    (are [x y] (= x y)
        true (parse-boolean "true")
        true (parse-boolean "True")
        false (parse-boolean "false")
        false (parse-boolean "False")
        false (parse-boolean "")
        false (parse-boolean "unknown")
        false (parse-boolean nil)))

