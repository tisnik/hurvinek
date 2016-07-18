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

(ns hurvinek.html-renderer-test
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


