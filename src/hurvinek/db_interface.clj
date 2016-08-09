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

(defn read-from-product-table
    "Read selected column from the given product id."
    [product-id what]
    (try
        (-> (jdbc/query db-spec/hurvinek-db
                            [(str "select " (name what) " from products where id=?") product-id])
            first
            (get what))
        (catch Exception e
            (println e)
            nil)))

(defn read-product-name
    "Read name of product for the given product id."
    [product-id]
    (read-from-product-table product-id :name))

(defn read-product-description
    "Read description of product for the given product id."
    [product-id]
    (read-from-product-table product-id :description))

(defn read-name-for-id
    [table-name id]
    (try
        (-> (jdbc/query db-spec/hurvinek-db
                            [(str "select name from " table-name " where id=?") id])
            first
            (get :name))
        (catch Exception e
            (println e)
            nil)))

(defn read-chapter-name
    "Read name of chapter for the given chapter id."
    [chapter-id]
    (read-name-for-id "chapters" chapter-id))

(defn read-group-name
    "Read name of group for the given group id."
    [group-id]
    (read-name-for-id "groups" group-id))

(defn read-component-name
    "Read name of component for the given component id."
    [component-id]
    (read-name-for-id "components" component-id))

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

(defn read-groups-per-chapter
    "Returns a map with all groups per chapters."
    [product-id chapter-list]
    (zipmap (map #(get % :id) chapter-list)
        (for [chapter chapter-list]
            (read-groups (:id chapter)))))

(defn count-for-table
    [table-name]
    (try
        (-> (jdbc/query db-spec/hurvinek-db [(str "select count(*) as cnt from " table-name)])
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

(defn add-new-product
    [product-name description]
    (if (or (empty? product-name) (empty? description))
        "Product name or description is empty"
        (try
            (jdbc/insert! db-spec/hurvinek-db
                :products {:name product-name
                           :description description})
            nil ; return value
            (catch Exception e
                (println e)
                e))))

(defn update-product
    [product-id product-name description]
    (if (or (empty? product-name) (empty? description))
        "Product name or description is empty"
        (try
            (jdbc/update! db-spec/hurvinek-db
                          :products {:name product-name
                                     :description description} ["id=?" product-id])
            nil ; return value
            (catch Exception e
                (println e)
                e))))

(defn add-new-chapter
    [product-id chapter-name]
    (if (empty? chapter-name)
        "Chapter name is empty"
        (try
            (jdbc/insert! db-spec/hurvinek-db
                :chapters {:product product-id
                           :name    chapter-name})
            nil ; return value
            (catch Exception e
                (println e)
                e))))

(defn update-chapter
    [product-id chapter-id chapter-name]
    (if (empty? chapter-name)
        "Chapter name is empty"
        (try
            (jdbc/update! db-spec/hurvinek-db
                          :chapters {:name chapter-name}
                                     ["id=?" chapter-id])
            nil ; return value
            (catch Exception e
                (println e)
                e))))

(defn add-new-group
    [product-id chapter-id group-name]
    (if (empty? group-name)
        "Group name is empty"
        (try
            (jdbc/insert! db-spec/hurvinek-db
                :groups {:chapter chapter-id
                         :name    group-name})
            nil ; return value
            (catch Exception e
                (println e)
                e))))

(defn read-components
    "Read list of all components for selected group."
    [group-id]
    (try
        (jdbc/query db-spec/hurvinek-db
                        ["select id, name from components where group_id=? order by name" group-id])
        (catch Exception e
            (println e)
            nil)))

(defn read-components-for-chapter
    [chapter-id]
    (try
        (jdbc/query db-spec/hurvinek-db
                        ["select components.id, components.name
                            from components, groups, chapters
                           where groups.chapter      = chapters.id
                             and components.group_id = groups.id
                             and chapters.id=?
                           order by components.name" chapter-id])))

(defn read-components-to-chapter
    [product-id]
    (try
        (jdbc/query db-spec/hurvinek-db
                        ["select components.name component, chapters.name chapter
                            from components, groups, chapters, products
                           where chapters.product    = products.id
                             and groups.chapter      = chapters.id
                             and components.group_id = groups.id
                             and products.name=?
                           order by components.name" product-id])))

(defn add-new-component
    [component-name group-id]
    (try
        (jdbc/insert! db-spec/hurvinek-db
                        :components {:name component-name
                                     :group_id group-id})
            nil ; return value
        (catch Exception e
            (println e)
            e)))

(defn delete-component
    [component-id]
    (try
        (jdbc/delete! db-spec/hurvinek-db
                        :components ["id=?" component-id])
            nil ; return value
        (catch Exception e
            (println e)
            e)))

(defn update-component
    [component-id component-name]
    (if (empty? component-name)
        "Component name is empty"
        (try
            (jdbc/update! db-spec/hurvinek-db
                          :components {:name component-name}
                                      ["id=?" component-id])
            nil ; return value
            (catch Exception e
                (println e)
                e))))

