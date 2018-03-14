;
;  (C) Copyright 2016, 2017, 2018  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns hurvinek.core
    "Core module that contains -main function called by Leiningen to start the application.")

(require '[ring.adapter.jetty      :as jetty])
(require '[ring.middleware.params  :as http-params])
(require '[ring.middleware.cookies :as cookies])

(require '[clojure.tools.cli       :as cli])
(require '[clojure.tools.logging   :as log])

(require '[hurvinek.server         :as server])
(require '[hurvinek.config         :as config])
(require '[hurvinek.middleware     :as middleware])

(def default-port
    "Default port on which hurvinek accepts all HTTP requests."
    "3000")

(def cli-options
    "Definitions of all command line options that are  currenty supported."
    ;; an option with a required argument
    [["-p" "--port   PORT"    "port number"    :id :port]
     ["-h" "--help"           "show this help" :id :help]])

; we need to load the configuration in advance so the 'app' could use it
(def configuration (config/load-configuration "hurvinek.ini"))

(def app
    "Definition of a Ring-based application behaviour."
    (-> server/handler            ; handle all events
        (middleware/inject-configuration configuration) ; inject configuration structure into the parameter
        cookies/wrap-cookies      ; we need to work with cookies
        http-params/wrap-params)) ; and to process request parameters, of course

(defn start-server
    "Start the HTTP server on the specified port."
    [port]
    (log/info "Starting the server at the port: " port)
    (jetty/run-jetty app {:port (read-string port)}))

(defn get-and-check-port
    "Accepts port number represented by string and throws AssertionError
     if port number is outside defined range."
    [port]
    (let [port-number (. Integer parseInt port)]
        (assert (> port-number 0))
        (assert (< port-number 65536))
        port))

(defn get-port
    "Returns specified port or default port if none is specified on the command line."
    [specified-port]
    (if (or (not specified-port) (not (string? specified-port)) (empty? specified-port))
        default-port
        (get-and-check-port specified-port)))

(defn show-help
    "Display brief help on the standard output."
    [all-options]
    (println "Usage:")
    (println (:summary all-options)))

(defn -main
    "Entry point to the Hurvinek server."
    [& args]
    (let [all-options      (cli/parse-opts args cli-options)
          options          (all-options :options)
          port             (options :port)]
          (if (:help options)
              (show-help all-options)
              (do
                  (config/print-configuration configuration)
                  (start-server (get-port port))))))

; finito

