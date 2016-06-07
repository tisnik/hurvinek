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

(ns hurvinek.db-spec
    "Namespace that contains configuration of all JDBC sources.")

(def hurvinek-db
    "Specification for SQLite database used for storing all components"
    {:classname   "org.sqlite.JDBC"
     :subprotocol "sqlite"
     :subname     "hurvinek.db"
    })

