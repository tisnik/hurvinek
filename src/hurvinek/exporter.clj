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
    "Convert list of products into plain text file. Fields are separated by TAB characters."
    [products]
    (->> (for [product products]
            (str (:id product) "\t" (:name product) "\t" (:description product)))
            (clojure.string/join "\n")))

(defn chapters->text
    "Convert list of chapters into plain text file. Fields are separated by TAB characters."
    [chapters]
    (->> (for [chapter chapters]
            (str (:id chapter) "\t" (:name chapter)))
            (clojure.string/join "\n")))

(defn groups->text
    "Convert list of groups into plain text file. Fields are separated by TAB characters."
    [groups]
    (->> (for [group groups]
            (str (:id group) "\t" (:name group)))
            (clojure.string/join "\n")))

(defn components->text
    "Convert list of components into plain text file. Fields are separated by TAB characters."
    [components]
    (->> (for [component components]
            (str (:id component) "\t" (:name component)))
            (clojure.string/join "\n")))

(defn components-to-chapter->text
    "Convert list of components into plain text file. Fields are separated by TAB characters."
    [components]
    (->> (for [component components]
            (str (:component component) "\t" (:chapter component)))
            (clojure.string/join "\n")))

(defn products->xml
    "Convert list of products into XML file."
    [products]
    (with-out-str
        (xml/emit {:tag :products :content
            (for [product products]
                {:tag :product :attrs {
                                   :id     (:id product)
                                   :name   (:name product)
                                   :description  (:description product)}})})))

(defn chapters->xml
    "Convert list of chapters into XML file."
    [chapters]
    (with-out-str
        (xml/emit {:tag :chapters :content
            (for [chapter chapters]
                {:tag :chapter :attrs {
                                   :id     (:id chapter)
                                   :name   (:name chapter)}})})))

(defn groups->xml
    "Convert list of groups into XML file."
    [groups]
    (with-out-str
        (xml/emit {:tag :groups :content
            (for [group groups]
                {:tag :group :attrs {
                                   :id     (:id group)
                                   :name   (:name group)}})})))

(defn components->xml
    "Convert list of components into XML file."
    [components]
    (with-out-str
        (xml/emit {:tag :components :content
            (for [component components]
                {:tag :component :attrs {
                                   :id     (:id component)
                                   :name   (:name component)}})})))

(defn components-to-chapter->xml
    "Convert list of components into XML file."
    [components]
    (with-out-str
        (xml/emit {:tag :components :content
            (for [component components]
                {:tag :component :attrs {
                                   :name    (:component component)
                                   :chapter (:chapter component)}})})))

(defn products->csv
    "Convert list of products into CSV file."
    [products]
    (data->csv (cons ["Product ID" "Product name" "Description"]
                     (for [product products]
                          [(:id product) (:name product) (:description product)]))))

(defn chapters->csv
    "Convert list of chapters into CSV file."
    [chapters]
    (data->csv (cons ["Chapter ID" "Chapter name"]
                     (for [chapter chapters]
                          [(:id chapter) (:name chapter)]))))

(defn groups->csv
    "Convert list of groups into CSV file."
    [groups]
    (data->csv (cons ["Group ID" "Group name"]
                     (for [group groups]
                          [(:id group) (:name group)]))))

(defn components->csv
    "Convert list of components into CSV file."
    [components]
    (data->csv (cons ["Component ID" "Component name"]
                     (for [component components]
                          [(:id component) (:name component)]))))

(defn components-to-chapter->csv
    "Convert list of components into CSV file."
    [components]
    (data->csv (cons ["Component name" "Chapter name"]
                     (for [component components]
                          [(:component component) (:chapter component)]))))

(defn product-output-data
    "Export product list into selected output format."
    [output-format product-list]
    (case output-format
        :json (data->json     product-list)
        :txt  (products->text product-list)
        :xml  (products->xml  product-list)
        :csv  (products->csv  product-list)
        :edn  (data->edn      product-list)))

(defn chapters-output-data
    "Export chapter list into selected output format."
    [output-format chapter-list]
    (case output-format
        :json (data->json     chapter-list)
        :txt  (chapters->text chapter-list)
        :xml  (chapters->xml  chapter-list)
        :csv  (chapters->csv  chapter-list)
        :edn  (data->edn      chapter-list)))

(defn groups-output-data
    "Export group list into selected output format."
    [output-format group-list]
    (case output-format
        :json (data->json     group-list)
        :txt  (groups->text   group-list)
        :xml  (groups->xml    group-list)
        :csv  (groups->csv    group-list)
        :edn  (data->edn      group-list)))

(defn components-output-data
    "Export component list into selected output format."
    [output-format component-list]
    (case output-format
        :json (data->json        component-list)
        :txt  (components->text  component-list)
        :xml  (components->xml   component-list)
        :csv  (components->csv   component-list)
        :edn  (data->edn         component-list)))

(defn components-to-chapter-mapping-output-data
    "Export component to chapter mapping into selected output format."
    [output-format component-list]
    (case output-format
        :json (data->json        component-list)
        :txt  (components-to-chapter->text  component-list)
        :xml  (components-to-chapter->xml   component-list)
        :csv  (components-to-chapter->csv   component-list)
        :edn  (data->edn         component-list)))

