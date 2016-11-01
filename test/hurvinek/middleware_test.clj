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

(ns hurvinek.middleware-test
  (:require [clojure.test        :refer :all]
            [hurvinek.middleware :refer :all]))

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

(deftest test-inject-configuration-existence
    "Check that the hurvinek.middleware/inject-configuration definition exists."
    (testing "if the hurvinek.middleware/inject-configuration definition exists."
        (is (callable? 'hurvinek.middleware/inject-configuration))))

;
; Tests for function behaviours.
;

(deftest test-inject-confuguration-1
    "Check the behaviour of function hurvinek.middleware/inject-configuration."
    (testing "The function hurvinek.middleware/inject-configuration."
        (let [function (inject-configuration (fn [x] x) :cfg)]
            (are [x y] (= x y)
                {:configuration :cfg} (function nil)
                {:configuration :cfg} (function {})
                {:configuration :cfg :foo :bar} (function {:foo :bar})))))

(deftest test-inject-confuguration-2
    "Check the behaviour of function hurvinek.middleware/inject-configuration."
    (testing "The function hurvinek.middleware/inject-configuration."
        (let [function (inject-configuration (fn [x] nil) :cfg)]
            (are [x y] (= x y)
                nil (function nil)
                nil (function {})
                nil (function {:foo :bar})
                nil (function {:foo :bar :baz :bar})))))

(deftest test-inject-confuguration-not-NPE
    "Check the behaviour of function hurvinek.middleware/inject-configuration."
    (testing "The function hurvinek.middleware/inject-configuration."
        (let [function (inject-configuration (fn [x] x) nil)]
            (are [x y] (= x y)
                {:configuration nil} (function nil)
                {:configuration nil} (function {})
                {:configuration nil :foo :bar} (function {:foo :bar})))))

