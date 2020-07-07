;
;  (C) Copyright 2016, 2017, 2018, 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns hurvinek.rest-api
    "REST API handlers")

(require '[ring.util.response     :as http-response])
(require '[clojure.data.json      :as json])

(require '[hurvinek.process-info  :as process-info])

(defn send-rest-api-response
    [response]
    (-> (http-response/response (json/write-str response))
        (http-response/content-type "application/json")))

(defn process-api-info
    "REST API handler for the /api/info"
    [request]
    (let [response {:toasterNotifications [(str "info|Api response|<strong>Hurvinek</strong> api v1")]
                    :configuration (:configuration request)}]
        (send-rest-api-response response)))

(defn process-status
    "REST API handler for the /api/status"
    [request]
    (let [response {:properties     (process-info/read-properties)
                    :pid            (process-info/get-current-pid)}]
         (send-rest-api-response response)))

(defn process-configuration
    "REST API handler for the /api/configuration"
    [request]
    (let [response (-> request :configuration)]
         (send-rest-api-response response)))

