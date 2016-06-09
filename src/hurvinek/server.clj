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

(ns hurvinek.server
    "Server module with functions to accept requests and send response back to users via HTTP.")

(require '[ring.util.response     :as http-response])
(require '[clojure.data.json      :as json])
(require '[clojure.xml            :as xml])
(require '[clojure.data.csv       :as csv])

(require '[hurvinek.html-renderer :as html-renderer])
(require '[hurvinek.db-interface  :as db-interface])

(defn println-and-flush
    "Original (println) has problem with syncing when it's called from more threads.
     This function is a bit better because it flushes all output immediatelly."
    [& more]
    (.write *out* (str (clojure.string/join " " more) "\n"))
    (flush))

(defn get-title
    [request]
    (-> (:configuration request)
        :display
        :app-name))

(defn get-url-prefix
    [request]
    (-> (:configuration request)
        :server
        :url-prefix))

(defn finish-processing-front-page
    [request title]
    (let [params        (:params request)
          url-prefix    (get-url-prefix request)]
          (-> (http-response/response (html-renderer/render-front-page url-prefix title))
              (http-response/content-type "text/html"))))

(defn finish-processing-help-page
    [request title]
    (let [params        (:params request)
          url-prefix    (get-url-prefix request)]
          (-> (http-response/response (html-renderer/render-help-page url-prefix title))
              (http-response/content-type "text/html"))))

(defn finish-processing-database-statistic-page
    [request title db-stat]
    (let [params        (:params request)
          url-prefix    (get-url-prefix request)]
          (-> (http-response/response (html-renderer/render-database-statistic-page url-prefix title db-stat))
              (http-response/content-type "text/html"))))

(defn finish-processing-export-database-page
    [request title]
    (let [params        (:params request)
          url-prefix    (get-url-prefix request)]
          (-> (http-response/response (html-renderer/render-export-database-page url-prefix title))
              (http-response/content-type "text/html"))))

(defn finish-processing-product-list
    [request title product-list & {:keys [message-type message]}]
    (let [params        (:params request)
          url-prefix    (get-url-prefix request)]
          (-> (http-response/response (html-renderer/render-product-list url-prefix title product-list :message-type message-type :message message))
              (http-response/content-type "text/html"))))

(defn finish-processing-edit-product
    [request title product-id product-name description & {:keys [message-type message]}]
    (let [params        (:params request)
          url-prefix    (get-url-prefix request)]
          (-> (http-response/response (html-renderer/render-edit-product url-prefix title product-id product-name description :message-type message-type :message message))
              (http-response/content-type "text/html"))))

(defn finish-processing-chapter-list
    [request title product-id product-name chapter-list & {:keys [message-type message]}]
    (let [params        (:params request)
          url-prefix    (get-url-prefix request)]
          (-> (http-response/response (html-renderer/render-chapter-list url-prefix title product-id product-name chapter-list :message-type message-type :message message))
              (http-response/content-type "text/html"))))

(defn finish-processing-group-list
    [request title product-id chapter-id product-name chapter-name group-list]
    (let [params        (:params request)
          url-prefix    (get-url-prefix request)]
          (-> (http-response/response (html-renderer/render-group-list url-prefix title product-id chapter-id product-name chapter-name group-list))
              (http-response/content-type "text/html"))))

(defn display-rename-chapter-dialog
    [request title product-id chapter-id]
    (let [product-name   (db-interface/read-product-name product-id)
          chapter-name   (db-interface/read-chapter-name chapter-id)]
        (println product-name)
        (println chapter-name)
        (finish-processing-chapter-list request title product-id product-name nil)))

(defn process-front-page
    "Function that prepares data for the front page."
    [request title]
    (finish-processing-front-page request title))

(defn process-help-page
    [request title]
    (finish-processing-help-page request title))

(defn process-database-statistic
    [request title]
    (let [db-stat (db-interface/read-database-statistic)]
        (print "DB stat:" db-stat)
        (finish-processing-database-statistic-page request title db-stat)))

(defn process-export-database
    [request title]
    (finish-processing-export-database-page request title))

(defn process-select-product-page
    "Function that prepares data for the 'Select product' page."
    [request title]
    (let [product-list (db-interface/read-products)]
        (println "Product list:")
        (clojure.pprint/pprint product-list)
        (finish-processing-product-list request title product-list)))

(defn process-product-page
    "Function that prepares data for the selected product page."
    [request title]
    (let [params         (:params request)
          product-id     (get params "product-id")
          product-name   (db-interface/read-product-name product-id)
          chapter-list   (db-interface/read-chapters product-id)]
          (println "Product ID  " product-id)
          (println "Product name "product-name)
          (println "Chapters    " chapter-list)
          (finish-processing-chapter-list request title product-id product-name chapter-list)))

(defn process-add-new-product-page
    [request title]
    (let [params         (:params request)
          product-name   (get params "product-name")
          description    (get params "description")
          insert-result  (db-interface/add-new-product product-name description)
          product-list   (db-interface/read-products)]
          (cond
              (empty? product-name) (finish-processing-product-list request title product-list :message-type "danger" :message "Product name is not specified")
              (empty? description)  (finish-processing-product-list request title product-list :message-type "danger" :message "Product description is not specified")
              insert-result         (finish-processing-product-list request title product-list :message-type "danger" :message (str insert-result))
              :else                 (finish-processing-product-list request title product-list :message-type "info" :message (str "Product " product-name " has been added into the database"))
          )))

(defn process-add-new-chapter-page
    [request title]
    (let [params         (:params request)
          product-id     (get params "product-id")
          chapter-name   (get params "chapter-name")
          product-name   (db-interface/read-product-name product-id)
          insert-result  (db-interface/add-new-chapter product-id chapter-name)
          chapter-list   (db-interface/read-chapters product-id)]
          (if (not chapter-name)
              (finish-processing-chapter-list request title product-id product-name chapter-list)
              (cond
                  (empty? chapter-name) (finish-processing-chapter-list request title product-id product-name chapter-list :message-type "danger" :message "Chapter name is not specified")
                  insert-result         (finish-processing-chapter-list request title product-id product-name chapter-list :message-type "danger" :message (str insert-result))
                  :else                 (finish-processing-chapter-list request title product-id product-name chapter-list :message-type "info" :message (str "Chapter " chapter-name " has been added into the database"))
              ))))

(defn process-edit-product-page
    [request title]
    (let [params         (:params request)
          product-id     (get params "product-id")
          entered-name   (get params "product-name")
          entered-description (get params "description")
          update-result  (db-interface/update-product product-id entered-name entered-description)
          product-name   (db-interface/read-product-name product-id)
          description    (db-interface/read-product-description product-id)]
          (println "Product ID  " product-id)
          (println "Product name" product-name)
          (println "Description " description)
          (println "Entered product name" entered-name)
          (println "Entered description " entered-description)

          (if (and entered-name entered-description)
              (cond
                  (empty? entered-name)         (finish-processing-edit-product request title product-id product-name description :message-type "danger" :message "Product name is not specified")
                  (empty? entered-description)  (finish-processing-edit-product request title product-id product-name description :message-type "danger" :message "Product description is not specified")
                  update-result                 (finish-processing-edit-product request title product-id product-name description :message-type "danger" :message (str update-result))
                  :else                         (finish-processing-edit-product request title product-id product-name description :message-type "info"   :message (str "Product " product-name " has been updated")))
              (finish-processing-edit-product request title product-id product-name description))))

(defn process-chapter-page
    "Function that prepares data for the selected product and chapter page."
    [request title]
    (let [params         (:params request)
          product-id     (get params "product-id")
          chapter-id     (get params "chapter-id")
          product-name   (db-interface/read-product-name product-id)
          chapter-name   (db-interface/read-chapter-name chapter-id)
          group-list     (db-interface/read-groups chapter-id)]
          (println "Product ID   " product-id)
          (println "Product name " product-name)
          (println "Chapter ID   " chapter-id)
          (println "Chapter name " chapter-name)
          (finish-processing-group-list request title product-id chapter-id product-name chapter-name group-list)))

(defn process-group-page
    "Function that prepares data for the selected product, chapter, and group page."
    [request title]
    )

(defn return-file
    "Creates HTTP response containing content of specified file.
     Special value nil / HTTP response 404 is returned in case of any I/O error."
    [file-name content-type]
    (let [file (new java.io.File "www" file-name)]
        (println-and-flush "Returning file " (.getAbsolutePath file))
        (if (.exists file)
            (-> (http-response/response file)
                (http-response/content-type content-type))
            (println-and-flush "return-file(): can not access file: " (.getName file)))))

(defn handler
    "Handler that is called by Ring for all requests received from user(s)."
    [request]
    (println-and-flush "request URI: " (request :uri))
    (let [uri          (request :uri)
          title        (get-title request)]
        (condp = uri
            "/favicon.ico"                (return-file "favicon.ico"       "image/x-icon")
            "/bootstrap.min.css"          (return-file "bootstrap.min.css" "text/css")
            "/hurvinek.css"               (return-file "hurvinek.css"      "text/css")
            "/bootstrap.min.js"           (return-file "bootstrap.min.js"  "application/javascript")
            "/"                           (process-front-page           request title)
            "/help"                       (process-help-page            request title)
            "/database-statistic"         (process-database-statistic   request title)
            "/export-database"            (process-export-database      request title)
            "/select-product"             (process-select-product-page  request title)
            "/product"                    (process-product-page         request title)
            "/add-new-product"            (process-add-new-product-page request title)
            "/add-new-chapter"            (process-add-new-chapter-page request title)
            "/edit-product"               (process-edit-product-page    request title)
            "/chapter"                    (process-chapter-page         request title)
            "/group"                      (process-group-page           request title)
            )))

