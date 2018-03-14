;
;  (C) Copyright 2016, 2017, 2018  Pavel Tisnovsky
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


(deftest test-read-groups-existence
    "Check that the hurvinek.db-interface/read-groups definition exists."
    (testing "if the hurvinek.db-interface/read-groups definition exists."
        (is (callable? 'hurvinek.db-interface/read-groups))))


(deftest test-read-groups-per-chapter-existence
    "Check that the hurvinek.db-interface/read-groups-per-chapter definition exists."
    (testing "if the hurvinek.db-interface/read-groups-per-chapter definition exists."
        (is (callable? 'hurvinek.db-interface/read-groups-per-chapter))))


(deftest test-count-for-table-existence
    "Check that the hurvinek.db-interface/count-for-table definition exists."
    (testing "if the hurvinek.db-interface/count-for-table definition exists."
        (is (callable? 'hurvinek.db-interface/count-for-table))))


(deftest test-read-database-statistic-existence
    "Check that the hurvinek.db-interface/read-database-statistic definition exists."
    (testing "if the hurvinek.db-interface/read-database-statistic definition exists."
        (is (callable? 'hurvinek.db-interface/read-database-statistic))))


(deftest test-add-new-product-existence
    "Check that the hurvinek.db-interface/add-new-product definition exists."
    (testing "if the hurvinek.db-interface/add-new-product definition exists."
        (is (callable? 'hurvinek.db-interface/add-new-product))))


(deftest test-update-product-existence
    "Check that the hurvinek.db-interface/update-product definition exists."
    (testing "if the hurvinek.db-interface/update-product definition exists."
        (is (callable? 'hurvinek.db-interface/update-product))))


(deftest test-add-new-chapter-existence
    "Check that the hurvinek.db-interface/add-new-chapter definition exists."
    (testing "if the hurvinek.db-interface/add-new-chapter definition exists."
        (is (callable? 'hurvinek.db-interface/add-new-chapter))))


(deftest test-update-chapter-existence
    "Check that the hurvinek.db-interface/update-chapter definition exists."
    (testing "if the hurvinek.db-interface/update-chapter definition exists."
        (is (callable? 'hurvinek.db-interface/update-chapter))))


(deftest test-add-new-group-existence
    "Check that the hurvinek.db-interface/add-new-group definition exists."
    (testing "if the hurvinek.db-interface/add-new-group definition exists."
        (is (callable? 'hurvinek.db-interface/add-new-group))))


(deftest test-read-components-existence
    "Check that the hurvinek.db-interface/read-components definition exists."
    (testing "if the hurvinek.db-interface/read-components definition exists."
        (is (callable? 'hurvinek.db-interface/read-components))))


(deftest test-read-components-for-chapter-existence
    "Check that the hurvinek.db-interface/read-components-for-chapter definition exists."
    (testing "if the hurvinek.db-interface/read-components-for-chapter definition exists."
        (is (callable? 'hurvinek.db-interface/read-components-for-chapter))))


(deftest test-add-new-component-existence
    "Check that the hurvinek.db-interface/add-new-component definition exists."
    (testing "if the hurvinek.db-interface/add-new-component definition exists."
        (is (callable? 'hurvinek.db-interface/add-new-component))))


(deftest test-delete-component-existence
    "Check that the hurvinek.db-interface/delete-component definition exists."
    (testing "if the hurvinek.db-interface/delete-component definition exists."
        (is (callable? 'hurvinek.db-interface/delete-component))))


(deftest test-update-component-existence
    "Check that the hurvinek.db-interface/update-component definition exists."
    (testing "if the hurvinek.db-interface/update-component definition exists."
        (is (callable? 'hurvinek.db-interface/update-component))))

