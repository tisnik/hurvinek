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
  (:require [clojure.test       :refer :all]
            [hurvinek.core      :refer :all]
            [ring.adapter.jetty :as    jetty]
            [clojure.tools.cli  :as    cli]))

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

;
; Tests for function behaviours
;

(deftest test-get-port
    "Check the function hurvinek.core/get-port."
    (testing "the function hurvinek.core/get-port."
        (is (= (get-port "1")     "1"))
        (is (= (get-port "2")     "2"))
        (is (= (get-port "3000")  "3000"))
        (is (= (get-port "65534") "65534"))
        (is (= (get-port "65535") "65535"))))

(deftest test-get-port-special-cases
    "Check the function hurvinek.core/get-port."
    (testing "the function hurvinek.core/get-port."
        (is (= (get-port nil)     "3000"))
        (is (= (get-port "")      "3000"))
        (is (= (get-port 0)       "3000"))
        (is (= (get-port 1)       "3000"))
        (is (= (get-port 65535)   "3000"))
        (is (= (get-port 65536)   "3000"))))

(deftest test-get-port-negative
    "Check the function hurvinek.core/get-port."
    (testing "the function hurvinek.core/get-port."
        (is (thrown? AssertionError (get-port "0")))
        (is (thrown? AssertionError (get-port "-1")))
        (is (thrown? AssertionError (get-port "-2")))
        (is (thrown? AssertionError (get-port "65536")))
        (is (thrown? AssertionError (get-port "65537")))
        (is (thrown? AssertionError (get-port "1000000")))))

(deftest test-get-and-check-port
    "Check the function hurvinek.core/get-and-check-port."
    (testing "the function hurvinek.core/get-and-check-port."
        (is (= (get-and-check-port "1")     "1"))
        (is (= (get-and-check-port "2")     "2"))
        (is (= (get-and-check-port "65534") "65534"))
        (is (= (get-and-check-port "65535") "65535"))))

(deftest test-get-and-check-port-negative
    "Check the function hurvinek.core/get-and-check-port."
    (testing "the function hurvinek.core/get-and-check-port."
        (is (thrown? AssertionError (get-and-check-port "-1")))
        (is (thrown? AssertionError (get-and-check-port "0")))
        (is (thrown? AssertionError (get-and-check-port "65536")))
        (is (thrown? AssertionError (get-and-check-port "65537")))
        (is (thrown? AssertionError (get-and-check-port "1000000")))))

(deftest test-start-server-positive-1
    (testing "hurvinek.core/start-server"
        ; use mock instead of jetty/run-jetty
        (with-redefs [jetty/run-jetty (fn [app port] port)]
            (is (= {:port 1}     (start-server "1")))
            (is (= {:port 2}     (start-server "2")))
            (is (= {:port 1000}  (start-server "1000")))
            (is (= {:port 65534} (start-server "65534")))
            (is (= {:port 65535} (start-server "65535"))))))

(deftest test-start-server-positive-2
    (testing "hurvinek.core/start-server"
        ; use mock instead of jetty/run-jetty
        (with-redefs [jetty/run-jetty (fn [app port] app)]
            (is (= app (start-server "1")))
            (is (= app (start-server "2")))
            (is (= app (start-server "1000")))
            (is (= app (start-server "65534")))
            (is (= app (start-server "65535"))))))

(deftest test-start-server-wrong-port-number
    (testing "hurvinek.core/start-server"
        ; use mock instead of jetty/run-jetty
        (with-redefs [jetty/run-jetty (fn [app port] port)]
            (is (= {:port -1}      (start-server "-1")))
            (is (= {:port 0}       (start-server "0")))
            (is (= {:port 65536}   (start-server "65536")))
            (is (= {:port 65537}   (start-server "65537")))
            (is (= {:port 1000000} (start-server "1000000"))))))

(deftest test-show-help
    (testing "hurvinek.core/show-help"
        (let [options (cli/parse-opts nil cli-options)]
            (is (= (with-out-str (show-help options))
                   (str
                   "Usage:\n"
                   "  -p, --port   PORT  port number\n"
                   "  -h, --help         show this help\n"))))))

(deftest test-main-1
    (testing "hurvinek.core/-main"
        (are [x y] (= x y)
           (with-out-str (-main "-h"))
           (str
           "{:display {:app-name \"Hurvinek\"}, :server {:url-prefix \"/\"}}\n"
           "Usage:\n"
           "  -p, --port   PORT  port number\n"
           "  -h, --help         show this help\n")
           (with-out-str (-main "--help"))
           (str
           "{:display {:app-name \"Hurvinek\"}, :server {:url-prefix \"/\"}}\n"
           "Usage:\n"
           "  -p, --port   PORT  port number\n"
           "  -h, --help         show this help\n"))))

(deftest test-main-2
    (testing "hurvinek.core/-main"
        ; use mock instead of jetty/run-jetty
        (with-redefs [jetty/run-jetty (fn [app port] app)]
        ; use mock instead of jetty/run-jetty
        (is (= app (-main))))))

