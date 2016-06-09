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

(ns hurvinek.db-interface
    "Namespace that contains interface to the database.")

(require '[clojure.java.jdbc       :as jdbc])

(require '[hurvinek.db-spec     :as db-spec])

(defn read-products
    "Read list of all products."
    []
    (try
        (jdbc/query db-spec/hurvinek-db
                        ["select id, name, description from products order by name"])
        (catch Exception e
            (println e)
            nil)))

(defn read-product-name
    "Read name of product for the given product id."
    [product-id]
    (try
        (->
            (jdbc/query db-spec/hurvinek-db
                            ["select name from products where id=?" product-id])
            first
            (get :name))
        (catch Exception e
            (println e)
            nil)))

(defn read-chapter-name
    "Read name of chapter for the given chapter id."
    [chapter-id]
    (try
        (->
            (jdbc/query db-spec/hurvinek-db
                            ["select name from chapters where id=?" chapter-id])
            first
            (get :name))
        (catch Exception e
            (println e)
            nil)))

(defn read-chapters
    "Read list of all chapters for selected product."
    [product-id]
    (try
        (jdbc/query db-spec/hurvinek-db
                        ["select id, name from chapters where product=? order by name" product-id])
        (catch Exception e
            (println e)
            nil)))

(defn read-groups
    "Read list of all groups for selected chapter (and product - it is implicit)."
    [chapter-id]
    (try
        (jdbc/query db-spec/hurvinek-db
                        ["select id, name from groups where chapter=? order by name" chapter-id])
        (catch Exception e
            (println e)
            nil)))

(defn count-for-table
    [table-name]
    (try
        (->
            (jdbc/query db-spec/hurvinek-db [(str "select count(*) as cnt from " table-name)])
            (first)
            (get :cnt))
        (catch Exception e
            (println e)
            0)))

(defn read-database-statistic
    []
    {:products   (count-for-table "products")
     :chapters   (count-for-table "chapters")
     :groups     (count-for-table "groups")
     :components (count-for-table "components")})

