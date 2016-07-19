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

(ns hurvinek.exporter
    "Module with functions to export data into selected format.")

(require '[clojure.data.json      :as json])
(require '[clojure.xml            :as xml])
(require '[clojure.data.csv       :as csv])

(defn data->json
    "Convert/format any data to JSON format."
    [products]
    (json/write-str products))

(defn data->edn
    "Convert/format any data to EDN format."
    [data]
    (with-out-str
        (clojure.pprint/pprint data)))

(defn data->csv
    "Convert/format any data to CSV format."
    [data]
    (with-out-str
        (csv/write-csv *out* data)))

(defn products->text
    [products]
    (->> (for [product products]
            (str (:id product) "\t" (:name product) "\t" (:description product)))
            (clojure.string/join "\n")))

(defn chapters->text
    [chapters]
    (->> (for [chapter chapters]
            (str (:id chapter) "\t" (:name chapter)))
            (clojure.string/join "\n")))

(defn groups->text
    [groups]
    (->> (for [group groups]
            (str (:id group) "\t" (:name group)))
            (clojure.string/join "\n")))

(defn components->text
    [components]
    (->> (for [component components]
            (str (:id component) "\t" (:name component)))
            (clojure.string/join "\n")))

