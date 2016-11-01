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

(ns hurvinek.utils
    "Various utility function used by other modules.")

(defn third
    "Simple utility function - returns third item from a given sequence."
    [coll]
    (nth coll 2 nil))

(defn fourth
    "Simple utility function - returns fourth item from a given sequence."
    [coll]
    (nth coll 3 nil))

(defn substring
    "Call method String.substring()."
    ([^String s from to]
     (.substring s from to))
    ([^String s from]
     (.substring s from)))

(defn startsWith
    "Call method String.startsWith()."
    [^String s pattern]
    (.startsWith s pattern))

(defn endsWith
    "Call method String.endsWith()."
    [^String s pattern]
    (.endsWith s pattern))

(defn contains
    "Call method String.contains()."
    [^String s pattern]
    (.contains s pattern))

(defn replaceAll
    "Call method String.replaceAll()."
    [^String s pattern replacement]
    (.replaceAll s pattern replacement))

(defn get-exception-message
    "Retrieve a message from given exception."
    [^java.lang.Exception exception]
    (.getMessage exception))

