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

(ns hurvinek.utils-test
  (:require [clojure.test :refer :all]
            [hurvinek.utils :refer :all]))

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

(deftest test-third-existence
    "Check that the hurvinek.utils/third definition exists."
    (testing "if the hurvinek.utils/third definition exists."
        (is (callable? 'hurvinek.utils/third))))


(deftest test-substring-existence
    "Check that the hurvinek.utils/substring definition exists."
    (testing "if the hurvinek.utils/substring definition exists."
        (is (callable? 'hurvinek.utils/substring))))


(deftest test-startsWith-existence
    "Check that the hurvinek.utils/startsWith definition exists."
    (testing "if the hurvinek.utils/startsWith definition exists."
        (is (callable? 'hurvinek.utils/startsWith))))


(deftest test-endsWith-existence
    "Check that the hurvinek.utils/endsWith definition exists."
    (testing "if the hurvinek.utils/endsWith definition exists."
        (is (callable? 'hurvinek.utils/endsWith))))


(deftest test-contains-existence
    "Check that the hurvinek.utils/contains definition exists."
    (testing "if the hurvinek.utils/contains definition exists."
        (is (callable? 'hurvinek.utils/contains))))


(deftest test-replaceAll-existence
    "Check that the hurvinek.utils/replaceAll definition exists."
    (testing "if the hurvinek.utils/replaceAll definition exists."
        (is (callable? 'hurvinek.utils/replaceAll))))

;
; Tests for behaviour of all functions
;

