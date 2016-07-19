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

(require '[hurvinek.html-renderer :as html-renderer])
(require '[hurvinek.exporter      :as exporter])
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

(defn finish-processing
    [render-function request title & rest-parameters]
    (let [params        (:params request)
          url-prefix    (get-url-prefix request)]
          (-> (http-response/response (apply render-function url-prefix title rest-parameters))
              (http-response/content-type "text/html"))))

(defn process-front-page
    "Function that prepares data for the front page."
    [request title]
    (finish-processing html-renderer/render-front-page request title))

(defn process-help-page
    [request title]
    (finish-processing html-renderer/render-help-page request title))

(defn process-database-statistic
    [request title]
    (let [db-stat (db-interface/read-database-statistic)]
        (print "DB stat:" db-stat)
        (finish-processing html-renderer/render-database-statistic-page request title db-stat)))

(defn process-export-database
    [request title]
    (finish-processing html-renderer/render-export-database-page request title))

(defn process-select-product-page
    "Function that prepares data for the 'Select product' page."
    [request title]
    (let [product-list (db-interface/read-products)]
        (finish-processing html-renderer/render-product-list request title product-list)))

(defn process-product-page
    "Function that prepares data for the selected product page."
    [request title]
    (let [params         (:params request)
          product-id     (get params "product-id")
          product-name   (db-interface/read-product-name product-id)
          chapter-list   (db-interface/read-chapters product-id)
          groups-per-chapter (db-interface/read-groups-per-chapter product-id chapter-list)]
          (finish-processing html-renderer/render-chapter-list request title product-id product-name chapter-list groups-per-chapter)))

(defn process-add-new-product-page
    [request title]
    (let [params         (:params request)
          product-name   (get params "product-name")
          description    (get params "description")
          insert-result  (db-interface/add-new-product product-name description)
          product-list   (db-interface/read-products)]
          (cond
              (empty? product-name) (finish-processing html-renderer/render-product-list request title product-list "danger" "Product name is not specified")
              (empty? description)  (finish-processing html-renderer/render-product-list request title product-list "danger" "Product description is not specified")
              insert-result         (finish-processing html-renderer/render-product-list request title product-list "danger" (.getMessage insert-result))
              :else                 (finish-processing html-renderer/render-product-list request title product-list "info"   (str "Product " product-name " has been added into the database"))
          )))

(defn process-add-new-chapter-page
    [request title]
    (let [params         (:params request)
          product-id     (get params "product-id")
          chapter-name   (get params "chapter-name")
          product-name   (db-interface/read-product-name product-id)
          insert-result  (db-interface/add-new-chapter product-id chapter-name)
          chapter-list   (db-interface/read-chapters product-id)
          groups-per-chapter (db-interface/read-groups-per-chapter product-id chapter-list)]
          (if (not chapter-name)
              (finish-processing html-renderer/render-chapter-list request title product-id product-name chapter-list groups-per-chapter)
              (cond
                  (empty? chapter-name) (finish-processing html-renderer/render-chapter-list request title product-id product-name chapter-list groups-per-chapter "danger" "Chapter name is not specified")
                  insert-result         (finish-processing html-renderer/render-chapter-list request title product-id product-name chapter-list groups-per-chapter "danger" (.getMessage insert-result))
                  :else                 (finish-processing html-renderer/render-chapter-list request title product-id product-name chapter-list groups-per-chapter "info"   (str "Chapter " chapter-name " has been added into the database"))
              ))))

(defn process-add-new-group-page
    [request title]
    (let [params         (:params request)
          product-id     (get params "product-id")
          chapter-id     (get params "chapter-id")
          group-name     (get params "group-name")
          return-to      (get params "return-to")
          product-name   (db-interface/read-product-name product-id)
          insert-result  (db-interface/add-new-group product-id chapter-id group-name)]
          (if (not group-name)
              (let [chapter-name   (db-interface/read-chapter-name chapter-id)
                    group-list     (db-interface/read-groups chapter-id)]
                  (finish-processing html-renderer/render-group-list request title product-id chapter-id product-name chapter-name group-list))
              (if (= return-to "chapter-list")
                  (let [chapter-list   (db-interface/read-chapters product-id)
                       groups-per-chapter (db-interface/read-groups-per-chapter product-id chapter-list)]
                       (cond
                           (empty? group-name) (finish-processing html-renderer/render-chapter-list request title product-id product-name chapter-list groups-per-chapter "danger" "Group name is not specified")
                           insert-result       (finish-processing html-renderer/render-chapter-list request title product-id product-name chapter-list groups-per-chapter "danger" (.getMessage insert-result))
                           :else               (finish-processing html-renderer/render-chapter-list request title product-id product-name chapter-list groups-per-chapter "info"   (str "Group " group-name " has been added into the database"))
                       ))
                  (let [chapter-name   (db-interface/read-chapter-name chapter-id)
                        group-list     (db-interface/read-groups chapter-id)]
                       (cond
                           (empty? group-name) (finish-processing html-renderer/render-group-list request title product-id chapter-id product-name chapter-name group-list "danger" "Group name is not specified")
                           insert-result       (finish-processing html-renderer/render-group-list request title product-id chapter-id product-name chapter-name group-list "danger" (.getMessage insert-result))
                           :else               (finish-processing html-renderer/render-group-list request title product-id chapter-id product-name chapter-name group-list "info"   (str "Group " group-name " has been added into the database"))
                       ))))))

(defn process-edit-chapter-page
    [request title]
    (let [params         (:params request)
          product-id     (get params "product-id")
          chapter-id     (get params "chapter-id")
          entered-name   (get params "chapter-name")
          update-result  (db-interface/update-chapter product-id chapter-id entered-name)
          product-name   (db-interface/read-product-name product-id)
          chapter-name   (db-interface/read-chapter-name chapter-id)]
        (if entered-name
            (cond
                (empty? entered-name) (finish-processing html-renderer/render-edit-chapter request title product-id product-name chapter-id chapter-name "danger" "Chapter name is not specified")
                update-result         (finish-processing html-renderer/render-edit-chapter request title product-id product-name chapter-id chapter-name "danger" (.getMessage update-result))
                :else                 (finish-processing html-renderer/render-edit-chapter request title product-id product-name chapter-id chapter-name "info"   "Chapter has been renamed"))
            (finish-processing html-renderer/render-edit-chapter request title product-id product-name chapter-id chapter-name))))

(defn process-edit-product-page
    [request title]
    (let [params         (:params request)
          product-id     (get params "product-id")
          entered-name   (get params "product-name")
          entered-description (get params "description")
          update-result  (db-interface/update-product product-id entered-name entered-description)
          product-name   (db-interface/read-product-name product-id)
          description    (db-interface/read-product-description product-id)]
          (if (and entered-name entered-description)
              (cond
                  (empty? entered-name)         (finish-processing html-renderer/render-edit-product request title product-id product-name description "danger" "Product name is not specified")
                  (empty? entered-description)  (finish-processing html-renderer/render-edit-product request title product-id product-name description "danger" "Product description is not specified")
                  update-result                 (finish-processing html-renderer/render-edit-product request title product-id product-name description "danger" (.getMessage update-result))
                  :else                         (finish-processing html-renderer/render-edit-product request title product-id product-name description "info"   (str "Product " product-name " has been updated")))
              (finish-processing html-renderer/render-edit-product request title product-id product-name description))))

(defn process-chapter-page
    "Function that prepares data html-renderer/render-chapter-page the selected product and chapter page."
    [request title]
    (let [params         (:params request)
          product-id     (get params "product-id")
          chapter-id     (get params "chapter-id")
          product-name   (db-interface/read-product-name product-id)
          chapter-name   (db-interface/read-chapter-name chapter-id)
          group-list     (db-interface/read-groups chapter-id)]
          (finish-processing html-renderer/render-group-list request title product-id chapter-id product-name chapter-name group-list)))

(defn process-group-page
    "Function that prepares data for the selected product, chapter, and group page."
    [request title]
    (let [params         (:params request)
          product-id     (get params "product-id")
          chapter-id     (get params "chapter-id")
          group-id       (get params "group-id")
          product-name   (db-interface/read-product-name product-id)
          chapter-name   (db-interface/read-chapter-name chapter-id)
          group-name     (db-interface/read-group-name group-id)
          component-list (db-interface/read-components group-id)]
          (finish-processing html-renderer/render-component-list request title product-id chapter-id group-id product-name chapter-name group-name component-list)))

(defn process-add-component
    [request title]
    (let [params         (:params request)
          product-id     (get params "product-id")
          chapter-id     (get params "chapter-id")
          group-id       (get params "group-id")
          component-name (get params "component-name")
          insert-result  (db-interface/add-new-component component-name group-id)
          product-name   (db-interface/read-product-name product-id)
          chapter-name   (db-interface/read-chapter-name chapter-id)
          group-name     (db-interface/read-group-name group-id)
          component-list (db-interface/read-components group-id)]
          (if insert-result
              (finish-processing html-renderer/render-component-list request title product-id chapter-id group-id product-name chapter-name group-name component-list "danger" (str insert-result))
              (finish-processing html-renderer/render-component-list request title product-id chapter-id group-id product-name chapter-name group-name component-list "info" (str "Component " component-name " has been added to the database")))))

(defn process-rename-component
    [request title]
    (let [params         (:params request)
          product-id     (get params "product-id")
          chapter-id     (get params "chapter-id")
          group-id       (get params "group-id")
          component-id   (get params "component-id")
          component-name (get params "component-name")
          update-result  (db-interface/update-component component-id component-name)
          product-name   (db-interface/read-product-name product-id)
          chapter-name   (db-interface/read-chapter-name chapter-id)
          group-name     (db-interface/read-group-name group-id)
          component-list (db-interface/read-components group-id)]
          (if update-result
              (finish-processing html-renderer/render-component-list request title product-id chapter-id group-id product-name chapter-name group-name component-list "danger" (str update-result))
              (finish-processing html-renderer/render-component-list request title product-id chapter-id group-id product-name chapter-name group-name component-list "info" (str "Component " component-name " has been renamed")))))

(defn process-delete-component
    [request title]
    (let [params         (:params request)
          product-id     (get params "product-id")
          chapter-id     (get params "chapter-id")
          group-id       (get params "group-id")
          component-id   (get params "component-id")
          component-name (db-interface/read-component-name component-id)
          delete-result  (db-interface/delete-component component-id)
          product-name   (db-interface/read-product-name product-id)
          chapter-name   (db-interface/read-chapter-name chapter-id)
          group-name     (db-interface/read-group-name group-id)
          component-list (db-interface/read-components group-id)]
          (if delete-result
              (finish-processing html-renderer/render-component-list request title product-id chapter-id group-id product-name chapter-name group-name component-list "danger" (.getMessage delete-result))
              (finish-processing html-renderer/render-component-list request title product-id chapter-id group-id product-name chapter-name group-name component-list "info" (str "Component " component-name " has been deleted")))))

(defn get-output-format
    [request]
    (let [output-format (-> (:params request) (get "format"))]
        (condp = output-format
            "json" :json
            "xml"  :xml
            "csv"  :csv
            "edn"  :edn
            "text" :txt
            "txt"  :txt
                   :json)))

(defn mime-type
    [output-format]
    (case output-format
        :json  "application/json"
        :edn   "application/edn"
        :csv   "text/csv"
        :txt   "text/plain"
        :xml   "text/xml"))

(defn process-list-of-products
    [request]
    (let [product-list  (db-interface/read-products)
          output-format (get-output-format request)
          output-data   (exporter/product-output-data output-format product-list)
          mime-type     (mime-type output-format)]
        (-> (http-response/response output-data)
            (http-response/content-type mime-type))))

(defn process-list-of-chapters
    [request]
    (let [params        (:params request)
          product-id    (get params "product-id")
          chapters      (db-interface/read-chapters product-id)
          output-format (get-output-format request)
          output-data   (exporter/chapters-output-data output-format chapters)
          mime-type     (mime-type output-format)]
        (-> (http-response/response output-data)
            (http-response/content-type mime-type))))

(defn process-list-of-groups
    [request]
    (let [params        (:params request)
          chapter-id    (get params "chapter-id")
          groups        (db-interface/read-groups chapter-id)
          output-format (get-output-format request)
          output-data   (exporter/groups-output-data output-format groups)
          mime-type     (mime-type output-format)]
        (-> (http-response/response output-data)
            (http-response/content-type mime-type))))

(defn process-list-of-components
    [request]
    (let [params        (:params request)
          chapter-id    (get params "chapter-id")
          components    (db-interface/read-components-for-chapter chapter-id)
          output-format (get-output-format request)
          output-data   (exporter/components-output-data output-format components)
          mime-type     (mime-type output-format)]
        (-> (http-response/response output-data)
            (http-response/content-type mime-type))))

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
            "/hurvinek.js"                (return-file "hurvinek.js"       "application/javascript")
            "/bootstrap.min.js"           (return-file "bootstrap.min.js"  "application/javascript")
            "/"                           (process-front-page           request title)
            "/help"                       (process-help-page            request title)
            "/database-statistic"         (process-database-statistic   request title)
            "/export-database"            (process-export-database      request title)
            "/select-product"             (process-select-product-page  request title)
            "/product"                    (process-product-page         request title)
            "/add-new-product"            (process-add-new-product-page request title)
            "/add-new-chapter"            (process-add-new-chapter-page request title)
            "/add-new-group"              (process-add-new-group-page   request title)
            "/edit-product"               (process-edit-product-page    request title)
            "/edit-chapter"               (process-edit-chapter-page    request title)
            "/chapter"                    (process-chapter-page         request title)
            "/group"                      (process-group-page           request title)
            "/add-new-component"          (process-add-component        request title)
            "/delete-component"           (process-delete-component     request title)
            "/rename-component"           (process-rename-component     request title)
            "/api/products"               (process-list-of-products     request)
            "/api/chapters"               (process-list-of-chapters     request)
            "/api/groups"                 (process-list-of-groups       request)
            "/api/components"             (process-list-of-components   request)
            )))

