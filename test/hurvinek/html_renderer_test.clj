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

(ns hurvinek.html-renderer-test
  (:require [clojure.test :refer :all]
            [hurvinek.html-renderer :refer :all]))

(require '[hiccup.page :as page])

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

(deftest test-render-html-header-existence
    "Check that the hurvinek.html-renderer/render-html-header definition exists."
    (testing "if the hurvinek.html-renderer/render-html-header definition exists."
        (is (callable? 'hurvinek.html-renderer/render-html-header))))


(deftest test-render-html-footer-existence
    "Check that the hurvinek.html-renderer/render-html-footer definition exists."
    (testing "if the hurvinek.html-renderer/render-html-footer definition exists."
        (is (callable? 'hurvinek.html-renderer/render-html-footer))))


(deftest test-render-navigation-bar-section-existence
    "Check that the hurvinek.html-renderer/render-navigation-bar-section definition exists."
    (testing "if the hurvinek.html-renderer/render-navigation-bar-section definition exists."
        (is (callable? 'hurvinek.html-renderer/render-navigation-bar-section))))


(deftest test-render-breadcrumb-existence
    "Check that the hurvinek.html-renderer/render-breadcrumb definition exists."
    (testing "if the hurvinek.html-renderer/render-breadcrumb definition exists."
        (is (callable? 'hurvinek.html-renderer/render-breadcrumb))))


(deftest test-render-back-link-existence
    "Check that the hurvinek.html-renderer/render-back-link definition exists."
    (testing "if the hurvinek.html-renderer/render-back-link definition exists."
        (is (callable? 'hurvinek.html-renderer/render-back-link))))


(deftest test-render-optional-message-existence
    "Check that the hurvinek.html-renderer/render-optional-message definition exists."
    (testing "if the hurvinek.html-renderer/render-optional-message definition exists."
        (is (callable? 'hurvinek.html-renderer/render-optional-message))))


(deftest test-render-front-page-existence
    "Check that the hurvinek.html-renderer/render-front-page definition exists."
    (testing "if the hurvinek.html-renderer/render-front-page definition exists."
        (is (callable? 'hurvinek.html-renderer/render-front-page))))


(deftest test-render-help-page-existence
    "Check that the hurvinek.html-renderer/render-help-page definition exists."
    (testing "if the hurvinek.html-renderer/render-help-page definition exists."
        (is (callable? 'hurvinek.html-renderer/render-help-page))))


(deftest test-render-database-statistic-page-existence
    "Check that the hurvinek.html-renderer/render-database-statistic-page definition exists."
    (testing "if the hurvinek.html-renderer/render-database-statistic-page definition exists."
        (is (callable? 'hurvinek.html-renderer/render-database-statistic-page))))


(deftest test-render-export-database-page-existence
    "Check that the hurvinek.html-renderer/render-export-database-page definition exists."
    (testing "if the hurvinek.html-renderer/render-export-database-page definition exists."
        (is (callable? 'hurvinek.html-renderer/render-export-database-page))))


(deftest test-render-product-list-existence
    "Check that the hurvinek.html-renderer/render-product-list definition exists."
    (testing "if the hurvinek.html-renderer/render-product-list definition exists."
        (is (callable? 'hurvinek.html-renderer/render-product-list))))


(deftest test-render-edit-product-existence
    "Check that the hurvinek.html-renderer/render-edit-product definition exists."
    (testing "if the hurvinek.html-renderer/render-edit-product definition exists."
        (is (callable? 'hurvinek.html-renderer/render-edit-product))))


(deftest test-render-chapter-list-existence
    "Check that the hurvinek.html-renderer/render-chapter-list definition exists."
    (testing "if the hurvinek.html-renderer/render-chapter-list definition exists."
        (is (callable? 'hurvinek.html-renderer/render-chapter-list))))


(deftest test-render-edit-chapter-existence
    "Check that the hurvinek.html-renderer/render-edit-chapter definition exists."
    (testing "if the hurvinek.html-renderer/render-edit-chapter definition exists."
        (is (callable? 'hurvinek.html-renderer/render-edit-chapter))))


(deftest test-render-group-list-existence
    "Check that the hurvinek.html-renderer/render-group-list definition exists."
    (testing "if the hurvinek.html-renderer/render-group-list definition exists."
        (is (callable? 'hurvinek.html-renderer/render-group-list))))


(deftest test-render-component-list-existence
    "Check that the hurvinek.html-renderer/render-component-list definition exists."
    (testing "if the hurvinek.html-renderer/render-component-list definition exists."
        (is (callable? 'hurvinek.html-renderer/render-component-list))))

;
; Tests for function behaviours
;

(deftest test-render-html-header
    "Checking the function hurvinek.html-renderer/render-html-header."
    (testing "the function hurvinek.html-renderer/render-html-header."
        (are [x y] (= (slurp x) y)
            "test/expected/html_header1.html" (page/xhtml (render-html-header "" ""))
            "test/expected/html_header2.html" (page/xhtml (render-html-header "" "title"))
            "test/expected/html_header3.html" (page/xhtml (render-html-header "http://10.20.30.40/" ""))
            "test/expected/html_header4.html" (page/xhtml (render-html-header "http://10.20.30.40/" "title")))))

(deftest test-render-html-footer
    "Checking the function hurvinek.html-renderer/render-html-footer."
    (testing "the function hurvinek.html-renderer/render-html-footer."
        (are [x y] (= (slurp x) y)
            "test/expected/html_footer1.html" (page/xhtml (render-html-footer)))))

(deftest test-render-navigation-bar-section
    "Checking the function hurvinek.html-renderer/render-navigation-bar-section."
    (testing "the function hurvinek.html-renderer/render-navigation-bar-section."
        (are [x y] (= (slurp x) y)
            "test/expected/navigation_bar_section1.html" (page/xhtml (render-navigation-bar-section "" ""))
            "test/expected/navigation_bar_section2.html" (page/xhtml (render-navigation-bar-section "http://www.example.org" ""))
            "test/expected/navigation_bar_section3.html" (page/xhtml (render-navigation-bar-section "http://www.example.org/prefix" ""))
            "test/expected/navigation_bar_section4.html" (page/xhtml (render-navigation-bar-section "" "Application title"))
            "test/expected/navigation_bar_section5.html" (page/xhtml (render-navigation-bar-section "http://www.example.org" "Application title"))
            "test/expected/navigation_bar_section6.html" (page/xhtml (render-navigation-bar-section "http://www.example.org/prefix" "Application title")))))

