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

(ns hurvinek.config
    "Module that contains all functions required to to load configuration from the INI file.")

(require '[clojure.tools.logging :as log])
(require '[clojure.pprint        :as pprint])
(require '[clojure-ini.core      :as clojure-ini])

(defn parse-int
    "Parse the given string as an integer number."
    [^String string]
    (java.lang.Integer/parseInt string))

(defn parse-float
    "Parse the given string as a float number."
    [^String string]
    (java.lang.Float/parseFloat string))

(defn parse-boolean
    "Parse the given string as a boolean value."
    [^String string]
    (or (= string "true")
        (= string "True")))

(defn load-configuration
    "Load configuration from the provided INI file."
    [^String ini-file-name]
    (clojure-ini/read-ini ini-file-name :keywordize? true))

(defn print-configuration
    "Print actual configuration to the output."
    [configuration]
    (pprint/pprint configuration))

