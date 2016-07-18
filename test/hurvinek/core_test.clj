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

