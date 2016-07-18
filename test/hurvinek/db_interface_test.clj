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

(ns hurvinek.db-interface-test
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

(deftest test-read-products-existence
    "Check that the hurvinek.db-interface/read-products definition exists."
    (testing "if the hurvinek.db-interface/read-products definition exists."
        (is (callable? 'hurvinek.db-interface/read-products))))


(deftest test-read-from-product-table-existence
    "Check that the hurvinek.db-interface/read-from-product-table definition exists."
    (testing "if the hurvinek.db-interface/read-from-product-table definition exists."
        (is (callable? 'hurvinek.db-interface/read-from-product-table))))


(deftest test-read-product-name-existence
    "Check that the hurvinek.db-interface/read-product-name definition exists."
    (testing "if the hurvinek.db-interface/read-product-name definition exists."
        (is (callable? 'hurvinek.db-interface/read-product-name))))


(deftest test-read-product-description-existence
    "Check that the hurvinek.db-interface/read-product-description definition exists."
    (testing "if the hurvinek.db-interface/read-product-description definition exists."
        (is (callable? 'hurvinek.db-interface/read-product-description))))


(deftest test-read-name-for-id-existence
    "Check that the hurvinek.db-interface/read-name-for-id definition exists."
    (testing "if the hurvinek.db-interface/read-name-for-id definition exists."
        (is (callable? 'hurvinek.db-interface/read-name-for-id))))


(deftest test-read-chapter-name-existence
    "Check that the hurvinek.db-interface/read-chapter-name definition exists."
    (testing "if the hurvinek.db-interface/read-chapter-name definition exists."
        (is (callable? 'hurvinek.db-interface/read-chapter-name))))


(deftest test-read-group-name-existence
    "Check that the hurvinek.db-interface/read-group-name definition exists."
    (testing "if the hurvinek.db-interface/read-group-name definition exists."
        (is (callable? 'hurvinek.db-interface/read-group-name))))


(deftest test-read-component-name-existence
    "Check that the hurvinek.db-interface/read-component-name definition exists."
    (testing "if the hurvinek.db-interface/read-component-name definition exists."
        (is (callable? 'hurvinek.db-interface/read-component-name))))


(deftest test-read-chapters-existence
    "Check that the hurvinek.db-interface/read-chapters definition exists."
    (testing "if the hurvinek.db-interface/read-chapters definition exists."
        (is (callable? 'hurvinek.db-interface/read-chapters))))


