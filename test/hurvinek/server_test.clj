;
;  (C) Copyright 2016, 2017, 2018, 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns hurvinek.server-test
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

(deftest test-println-and-flush-existence
    "Check that the hurvinek.server/println-and-flush definition exists."
    (testing "if the hurvinek.server/println-and-flush definition exists."
        (is (callable? 'hurvinek.server/println-and-flush))))


(deftest test-get-title-existence
    "Check that the hurvinek.server/get-title definition exists."
    (testing "if the hurvinek.server/get-title definition exists."
        (is (callable? 'hurvinek.server/get-title))))


(deftest test-get-url-prefix-existence
    "Check that the hurvinek.server/get-url-prefix definition exists."
    (testing "if the hurvinek.server/get-url-prefix definition exists."
        (is (callable? 'hurvinek.server/get-url-prefix))))


(deftest test-finish-processing-existence
    "Check that the hurvinek.server/finish-processing definition exists."
    (testing "if the hurvinek.server/finish-processing definition exists."
        (is (callable? 'hurvinek.server/finish-processing))))


(deftest test-process-front-page-existence
    "Check that the hurvinek.server/process-front-page definition exists."
    (testing "if the hurvinek.server/process-front-page definition exists."
        (is (callable? 'hurvinek.server/process-front-page))))


(deftest test-process-help-page-existence
    "Check that the hurvinek.server/process-help-page definition exists."
    (testing "if the hurvinek.server/process-help-page definition exists."
        (is (callable? 'hurvinek.server/process-help-page))))


(deftest test-process-database-statistic-existence
    "Check that the hurvinek.server/process-database-statistic definition exists."
    (testing "if the hurvinek.server/process-database-statistic definition exists."
        (is (callable? 'hurvinek.server/process-database-statistic))))


(deftest test-process-export-database-existence
    "Check that the hurvinek.server/process-export-database definition exists."
    (testing "if the hurvinek.server/process-export-database definition exists."
        (is (callable? 'hurvinek.server/process-export-database))))


(deftest test-process-select-product-page-existence
    "Check that the hurvinek.server/process-select-product-page definition exists."
    (testing "if the hurvinek.server/process-select-product-page definition exists."
        (is (callable? 'hurvinek.server/process-select-product-page))))


(deftest test-process-product-page-existence
    "Check that the hurvinek.server/process-product-page definition exists."
    (testing "if the hurvinek.server/process-product-page definition exists."
        (is (callable? 'hurvinek.server/process-product-page))))


(deftest test-process-add-new-product-page-existence
    "Check that the hurvinek.server/process-add-new-product-page definition exists."
    (testing "if the hurvinek.server/process-add-new-product-page definition exists."
        (is (callable? 'hurvinek.server/process-add-new-product-page))))


(deftest test-process-add-new-chapter-page-existence
    "Check that the hurvinek.server/process-add-new-chapter-page definition exists."
    (testing "if the hurvinek.server/process-add-new-chapter-page definition exists."
        (is (callable? 'hurvinek.server/process-add-new-chapter-page))))


(deftest test-process-add-new-group-page-existence
    "Check that the hurvinek.server/process-add-new-group-page definition exists."
    (testing "if the hurvinek.server/process-add-new-group-page definition exists."
        (is (callable? 'hurvinek.server/process-add-new-group-page))))


(deftest test-process-edit-chapter-page-existence
    "Check that the hurvinek.server/process-edit-chapter-page definition exists."
    (testing "if the hurvinek.server/process-edit-chapter-page definition exists."
        (is (callable? 'hurvinek.server/process-edit-chapter-page))))


(deftest test-process-edit-product-page-existence
    "Check that the hurvinek.server/process-edit-product-page definition exists."
    (testing "if the hurvinek.server/process-edit-product-page definition exists."
        (is (callable? 'hurvinek.server/process-edit-product-page))))


(deftest test-process-chapter-page-existence
    "Check that the hurvinek.server/process-chapter-page definition exists."
    (testing "if the hurvinek.server/process-chapter-page definition exists."
        (is (callable? 'hurvinek.server/process-chapter-page))))


(deftest test-process-group-page-existence
    "Check that the hurvinek.server/process-group-page definition exists."
    (testing "if the hurvinek.server/process-group-page definition exists."
        (is (callable? 'hurvinek.server/process-group-page))))


(deftest test-process-add-component-existence
    "Check that the hurvinek.server/process-add-component definition exists."
    (testing "if the hurvinek.server/process-add-component definition exists."
        (is (callable? 'hurvinek.server/process-add-component))))


(deftest test-process-rename-component-existence
    "Check that the hurvinek.server/process-rename-component definition exists."
    (testing "if the hurvinek.server/process-rename-component definition exists."
        (is (callable? 'hurvinek.server/process-rename-component))))


(deftest test-process-delete-component-existence
    "Check that the hurvinek.server/process-delete-component definition exists."
    (testing "if the hurvinek.server/process-delete-component definition exists."
        (is (callable? 'hurvinek.server/process-delete-component))))


(deftest test-get-output-format-existence
    "Check that the hurvinek.server/get-output-format definition exists."
    (testing "if the hurvinek.server/get-output-format definition exists."
        (is (callable? 'hurvinek.server/get-output-format))))


(deftest test-mime-type-existence
    "Check that the hurvinek.server/mime-type definition exists."
    (testing "if the hurvinek.server/mime-type definition exists."
        (is (callable? 'hurvinek.server/mime-type))))


(deftest test-process-list-of-products-existence
    "Check that the hurvinek.server/process-list-of-products definition exists."
    (testing "if the hurvinek.server/process-list-of-products definition exists."
        (is (callable? 'hurvinek.server/process-list-of-products))))


(deftest test-process-list-of-chapters-existence
    "Check that the hurvinek.server/process-list-of-chapters definition exists."
    (testing "if the hurvinek.server/process-list-of-chapters definition exists."
        (is (callable? 'hurvinek.server/process-list-of-chapters))))


(deftest test-process-list-of-groups-existence
    "Check that the hurvinek.server/process-list-of-groups definition exists."
    (testing "if the hurvinek.server/process-list-of-groups definition exists."
        (is (callable? 'hurvinek.server/process-list-of-groups))))


(deftest test-process-list-of-components-existence
    "Check that the hurvinek.server/process-list-of-components definition exists."
    (testing "if the hurvinek.server/process-list-of-components definition exists."
        (is (callable? 'hurvinek.server/process-list-of-components))))


(deftest test-return-file-existence
    "Check that the hurvinek.server/return-file definition exists."
    (testing "if the hurvinek.server/return-file definition exists."
        (is (callable? 'hurvinek.server/return-file))))


(deftest test-handler-existence
    "Check that the hurvinek.server/handler definition exists."
    (testing "if the hurvinek.server/handler definition exists."
        (is (callable? 'hurvinek.server/handler))))

