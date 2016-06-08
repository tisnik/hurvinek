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

