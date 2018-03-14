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

(ns hurvinek.exporter-test
  (:require [clojure.test :refer :all]
            [hurvinek.exporter :refer :all]))

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

(deftest test-data->json-existence
    "Check that the hurvinek.exporter/data->json definition exists."
    (testing "if the hurvinek.exporter/data->json definition exists."
        (is (callable? 'hurvinek.exporter/data->json))))


(deftest test-data->edn-existence
    "Check that the hurvinek.exporter/data->edn definition exists."
    (testing "if the hurvinek.exporter/data->edn definition exists."
        (is (callable? 'hurvinek.exporter/data->edn))))


(deftest test-data->csv-existence
    "Check that the hurvinek.exporter/data->csv definition exists."
    (testing "if the hurvinek.exporter/data->csv definition exists."
        (is (callable? 'hurvinek.exporter/data->csv))))


(deftest test-products->text-existence
    "Check that the hurvinek.exporter/products->text definition exists."
    (testing "if the hurvinek.exporter/products->text definition exists."
        (is (callable? 'hurvinek.exporter/products->text))))


(deftest test-chapters->text-existence
    "Check that the hurvinek.exporter/chapters->text definition exists."
    (testing "if the hurvinek.exporter/chapters->text definition exists."
        (is (callable? 'hurvinek.exporter/chapters->text))))


(deftest test-groups->text-existence
    "Check that the hurvinek.exporter/groups->text definition exists."
    (testing "if the hurvinek.exporter/groups->text definition exists."
        (is (callable? 'hurvinek.exporter/groups->text))))


(deftest test-components->text-existence
    "Check that the hurvinek.exporter/components->text definition exists."
    (testing "if the hurvinek.exporter/components->text definition exists."
        (is (callable? 'hurvinek.exporter/components->text))))


(deftest test-products->xml-existence
    "Check that the hurvinek.exporter/products->xml definition exists."
    (testing "if the hurvinek.exporter/products->xml definition exists."
        (is (callable? 'hurvinek.exporter/products->xml))))


(deftest test-chapters->xml-existence
    "Check that the hurvinek.exporter/chapters->xml definition exists."
    (testing "if the hurvinek.exporter/chapters->xml definition exists."
        (is (callable? 'hurvinek.exporter/chapters->xml))))


(deftest test-groups->xml-existence
    "Check that the hurvinek.exporter/groups->xml definition exists."
    (testing "if the hurvinek.exporter/groups->xml definition exists."
        (is (callable? 'hurvinek.exporter/groups->xml))))


(deftest test-components->xml-existence
    "Check that the hurvinek.exporter/components->xml definition exists."
    (testing "if the hurvinek.exporter/components->xml definition exists."
        (is (callable? 'hurvinek.exporter/components->xml))))


(deftest test-products->csv-existence
    "Check that the hurvinek.exporter/products->csv definition exists."
    (testing "if the hurvinek.exporter/products->csv definition exists."
        (is (callable? 'hurvinek.exporter/products->csv))))


(deftest test-chapters->csv-existence
    "Check that the hurvinek.exporter/chapters->csv definition exists."
    (testing "if the hurvinek.exporter/chapters->csv definition exists."
        (is (callable? 'hurvinek.exporter/chapters->csv))))


(deftest test-groups->csv-existence
    "Check that the hurvinek.exporter/groups->csv definition exists."
    (testing "if the hurvinek.exporter/groups->csv definition exists."
        (is (callable? 'hurvinek.exporter/groups->csv))))


(deftest test-components->csv-existence
    "Check that the hurvinek.exporter/components->csv definition exists."
    (testing "if the hurvinek.exporter/components->csv definition exists."
        (is (callable? 'hurvinek.exporter/components->csv))))


(deftest test-product-output-data-existence
    "Check that the hurvinek.exporter/product-output-data definition exists."
    (testing "if the hurvinek.exporter/product-output-data definition exists."
        (is (callable? 'hurvinek.exporter/product-output-data))))


(deftest test-chapters-output-data-existence
    "Check that the hurvinek.exporter/chapters-output-data definition exists."
    (testing "if the hurvinek.exporter/chapters-output-data definition exists."
        (is (callable? 'hurvinek.exporter/chapters-output-data))))


(deftest test-groups-output-data-existence
    "Check that the hurvinek.exporter/groups-output-data definition exists."
    (testing "if the hurvinek.exporter/groups-output-data definition exists."
        (is (callable? 'hurvinek.exporter/groups-output-data))))


(deftest test-components-output-data-existence
    "Check that the hurvinek.exporter/components-output-data definition exists."
    (testing "if the hurvinek.exporter/components-output-data definition exists."
        (is (callable? 'hurvinek.exporter/components-output-data))))

