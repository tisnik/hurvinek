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

(ns hurvinek.html-renderer
    "Module that contains functions used to render HTML pages sent back to the browser.")

(require '[hiccup.core            :as hiccup])
(require '[hiccup.page            :as page])
(require '[hiccup.form            :as form])

(defn render-html-header
    "Renders part of HTML page - the header."
    [url-prefix title]
    [:head
        [:title "Hurvinek"]
        [:meta {:name "Author"    :content "Pavel Tisnovsky"}]
        [:meta {:name "Generator" :content "Clojure"}]
        [:meta {:http-equiv "Content-type" :content "text/html; charset=utf-8"}]
        (page/include-css (str url-prefix "bootstrap.min.css"))
        (page/include-css (str url-prefix "hurvinek.css"))
        (page/include-js  (str url-prefix "bootstrap.min.js"))
    ] ; head
)

(defn render-html-footer
    "Renders part of HTML page - the footer."
    []
    [:div "<br /><br /><br /><br />Author: Pavel Tisnovsky &lt;<a href='mailto:ptisnovs@redhat.com'>ptisnovs@redhat.com</a>&gt"])

(defn render-navigation-bar-section
    "Renders whole navigation bar."
    [url-prefix title]
    [:nav {:class "navbar navbar-inverse navbar-fixed-top" :role "navigation"} ; use navbar-default instead of navbar-inverse
        [:div {:class "container-fluid"}
            [:div {:class "row"}
                [:div {:class "col-md-7"}
                    [:div {:class "navbar-header"}
                        [:a {:href url-prefix :class "navbar-brand"} title]
                    ] ; ./navbar-header
                ] ; col-md-7 ends
            ] ; row ends
        ] ; </div .container-fluid>
]); </nav>

(defn render-breadcrumb
    "Render the breadcumb at the top of page."
    (   [url-prefix]
        [:ol {:class "breadcrumb"}
             [:li [:a {:href url-prefix} "Hurvinek"]]])
    (   [url-prefix first-link first-name]
        [:ol {:class "breadcrumb"}
             [:li [:a {:href url-prefix} "Hurvinek"]]
             [:li [:a {:href first-link} first-name]]])
    (   [url-prefix first-link first-name second-link second-name]
        [:ol {:class "breadcrumb"}
             [:li [:a {:href url-prefix} "Hurvinek"]]
             [:li [:a {:href first-link} first-name]]
             [:li [:a {:href second-link} second-name]]]))

(defn render-back-link
    "Render link to go back."
    [link]
    [:div {:class "container-fluid"}
        [:br]
        [:br]
        [:br]
        [:a {:href link} "Back"]
    ])

(defn render-optional-message
    [message-type message]
    (if message
        [:div {:class "container-fluid"}
            [:div {:class (str "alert alert-" message-type)}
                message
            ]]))

(defn render-front-page
    "Render front page of this application."
    [url-prefix title]
    (page/xhtml
        (render-html-header url-prefix title)
        [:body
            [:div {:class "container"}
                (render-navigation-bar-section url-prefix title)
                (render-breadcrumb url-prefix)
                [:div {:class "container-fluid"}
                    [:h1 "Main menu"]
                    [:table {:class "table table-hover"}
                        [:tr [:td [:a {:href "select-product"} "Select product"]]]
                        [:tr [:td [:a {:href "database-statistic"} "Database statistic"]]]
                        [:tr [:td [:a {:href "export-database"} "Export database"]]]
                        [:tr [:td [:a {:href "help"} "Help"]]]
                    ]
                ]
                (render-html-footer)
            ] ; </div class="container">
        ] ; </body>
    ))

(defn render-help-page
    "Render help page for this application."
    [url-prefix title]
    (page/xhtml
        (render-html-header url-prefix title)
        [:body
            [:div {:class "container"}
                (render-navigation-bar-section url-prefix title)
                (render-breadcrumb url-prefix "help" "Help")
                [:div {:class "container-fluid"}
                    [:h2 "Help"]
                ]
                (render-back-link url-prefix)
                (render-html-footer)
            ] ; </div class="container">
        ] ; </body>
    ))

(defn render-database-statistic-page
    "Render database statistic."
    [url-prefix title db-stat]
    (page/xhtml
        (render-html-header url-prefix title)
        [:body
            [:div {:class "container"}
                (render-navigation-bar-section url-prefix title)
                (render-breadcrumb url-prefix "database-statistic" "Database statistic")
                [:div {:class "container-fluid"}
                    [:h2 "Database statistic"]
                    [:table {:class "table table-hover"}
                        [:tr [:td "Products"]   [:td (:products db-stat)]]
                        [:tr [:td "Chapters"]   [:td (:chapters db-stat)]]
                        [:tr [:td "Groups"]     [:td (:groups db-stat)]]
                        [:tr [:td "Components"] [:td (:components db-stat)]]
                    ]
                ]
                (render-back-link url-prefix)
                (render-html-footer)
            ] ; </div class="container">
        ] ; </body>
    ))

(defn render-export-database-page
    "Render menu with database export options."
    [url-prefix title]
    (page/xhtml
        (render-html-header url-prefix title)
        [:body
            [:div {:class "container"}
                (render-navigation-bar-section url-prefix title)
                (render-breadcrumb url-prefix "export-database" "Export database")
                [:div {:class "container-fluid"}
                    [:h2 "Export database"]
                ]
                (render-back-link url-prefix)
                (render-html-footer)
            ] ; </div class="container">
        ] ; </body>
    ))

(defn render-product-list
    "Render product list."
    [url-prefix title product-list & [message-type message]]
    (page/xhtml
        (render-html-header url-prefix title)
        [:body
            [:div {:class "container"}
                (render-navigation-bar-section url-prefix title)
                (render-breadcrumb url-prefix "select-product" "Product list")
                (render-optional-message message-type message)
                [:div {:class "container-fluid"}
                    [:h2 "Product list"]
                    [:table {:class "table table-hover"}
                        (for [product product-list]
                            [:tr
                                [:td [:a {:href (str "product?product-id=" (:id product))} (:name product)]]
                                [:td [:a {:href (str "product?product-id=" (:id product))} (:description product)]]
                                [:td [:a {:href (str "edit-product?product-id=" (:id product))} "Edit"]]])
                    ]
                    [:br]
                    [:h3 "New product"]
                    (form/form-to [:get "add-new-product"]
                        [:fieldset {:class "form-group"}
                            [:label {:for "product-name"} "Product name"]
                            [:input {:type "text" :class "form-control" :id "product-name" :name "product-name" :placeholder "product name"}]
                        ]
                        [:fieldset {:class "form-group"}
                            [:label {:for "product-description"} "Description for product"]
                            [:input {:type "text" :class "form-control" :id "product-description" :name "description" :placeholder "description"}]
                        ]
                        [:button {:type "submit" :class "btn btn-primary"} "Add new product"]
                    )
                ]
                (render-back-link url-prefix)
                (render-html-footer)
            ] ; </div class="container">
        ] ; </body>
    ))

(defn render-edit-product
    "Render the page with product."
    [url-prefix title product-id product-name description & [message-type message]]
    (page/xhtml
        (render-html-header url-prefix title)
        [:body
            [:div {:class "container"}
                (render-navigation-bar-section url-prefix title)
                (render-breadcrumb url-prefix "select-product" "Product list")
                (render-optional-message message-type message)
                [:div {:class "container-fluid"}
                    [:h3 "Edit product"]
                    (form/form-to [:get "edit-product"]
                        [:fieldset {:class "form-group"}
                            [:label {:for "product-id"} "Product id"]
                            [:input {:type "text" :class "form-control" :id "product-id" :name "product-id" :placeholder "product id" :value product-id :readonly "readonly"}]
                        ]
                        [:fieldset {:class "form-group"}
                            [:label {:for "product-name"} "Product name"]
                            [:input {:type "text" :class "form-control" :id "product-name" :name "product-name" :placeholder "product name" :value product-name}]
                        ]
                        [:fieldset {:class "form-group"}
                            [:label {:for "product-description"} "Description for product"]
                            [:input {:type "text" :class "form-control" :id "product-description" :name "description" :placeholder "description" :value description}]
                        ]
                        [:button {:type "submit" :class "btn btn-primary"} "Update"]
                    )
                ]
                (render-back-link url-prefix)
                (render-html-footer)
            ] ; </div class="container">
        ] ; </body>
    ))

(defn render-chapter-list
    "Render chapter list for selected product."
    [url-prefix title product-id product-name chapter-list & [message-type message]]
    (page/xhtml
        (render-html-header url-prefix title)
        [:body
            [:div {:class "container"}
                (render-navigation-bar-section url-prefix title)
                (render-breadcrumb url-prefix "select-product" (str "Product: " product-name))
                (render-optional-message message-type message)
                [:div {:class "container-fluid"}
                    [:h2 (str "Chapters for product " product-name)]
                    [:table {:style "border-collapse: separate; border-spacing: 10px;"}
                        (for [chapter chapter-list]
                            [:tr
                                [:td (:name chapter)]
                                [:td [:a {:href (str "edit-chapter?product-id=" product-id "&chapter-id=" (:id chapter) )} "rename"]]
                                [:td [:a {:href (str "chapter?product-id=" product-id "&chapter-id=" (:id chapter) "&action=grouplist")} "group list"]]])
                    ]]
                    [:br]
                    [:h3 "New chapter"]
                    (form/form-to [:get "add-new-chapter"]
                        (form/hidden-field "product-id" product-id)
                        [:fieldset {:class "form-group"}
                            [:label {:for "product-name"} "Name"]
                            [:input {:type "text" :class "form-control" :id "chapter-name" :name "chapter-name" :placeholder "chapter name"}]
                        ]
                        [:button {:type "submit" :class "btn btn-primary"} "Add new chapter"]
                    )
                (render-back-link (str url-prefix "select-product"))
                (render-html-footer)
            ] ; </div class="container">
        ] ; </body>
    ))

(defn render-edit-chapter
    "Render the page with product."
    [url-prefix title product-id product-name chapter-id chapter-name & [message-type message]]
    (page/xhtml
        (render-html-header url-prefix title)
        [:body
            [:div {:class "container"}
                (render-navigation-bar-section url-prefix title)
                (render-breadcrumb url-prefix "select-product" (str "Product: " product-name) (str "product?product-id=" product-id) chapter-name)
                (render-optional-message message-type message)
                [:div {:class "container-fluid"}
                    [:h3 (str "Edit chapter for product " product-name)]
                    (form/form-to [:get "edit-chapter"]
                        (form/hidden-field "product-id" product-id)
                        [:fieldset {:class "form-group"}
                            [:label {:for "chapter-id"} "Chapter id"]
                            [:input {:type "text" :class "form-control" :id "chapter-id" :name "chapter-id" :placeholder "product id" :value chapter-id :readonly "readonly"}]
                        ]
                        [:fieldset {:class "form-group"}
                            [:label {:for "chapter-name"} "Chapter name"]
                            [:input {:type "text" :class "form-control" :id "chapter-name" :name "chapter-name" :placeholder "product name" :value chapter-name}]
                        ]
                        [:button {:type "submit" :class "btn btn-primary"} "Update"]
                    )
                ]
                (render-back-link (str "product?product-id=" product-id))
                (render-html-footer)
            ] ; </div class="container">
        ] ; </body>
    ))

(defn render-group-list
    "Render group list for selected product and chapter."
    [url-prefix title product-id chapter-id product-name chapter-name group-list & [message-type message]]
    (page/xhtml
        (render-html-header url-prefix title)
        [:body
            [:div {:class "container"}
                (render-navigation-bar-section url-prefix title)
                (render-breadcrumb url-prefix "select-product" (str "Product: " product-name) (str "product?product-id=" product-id) chapter-name)
                [:div {:class "container-fluid"}
                [:h2 (str "Groups for product " product-name " and chapter " chapter-name)]
                [:table {:style "border-collapse: separate; border-spacing: 10px;"}
                    (for [group group-list]
                        [:tr
                            [:td [:a {:href (str "group?product-id=" product-id "&chapter-id=" chapter-id "&group-id=" (:id group))} (:name group)]]
                        ])
                ]]
                (render-back-link (str "product?product-id=" product-id))
                (render-html-footer)
            ] ; </div class="container">
        ] ; </body>
    ))

