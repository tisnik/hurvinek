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
    [url-prefix
     product-id product-name
     chapter-id chapter-name
     group-id group-name]
    [:ol {:class "breadcrumb"}
         [:li [:a {:href url-prefix} "Hurvinek"]]
         [:li product-name]]
         )

(defn render-front-page
    "Render front page of this application."
    [url-prefix title]
    (page/xhtml
        (render-html-header url-prefix title)
        [:body
            [:div {:class "container"}
                (render-navigation-bar-section url-prefix title)
                (render-breadcrumb url-prefix nil nil nil nil nil nil)
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

(defn render-product-list
    "Render product list."
    [url-prefix title product-list]
    (page/xhtml
        (render-html-header url-prefix title)
        [:body
            [:div {:class "container"}
                (render-navigation-bar-section url-prefix title)
                (render-breadcrumb url-prefix nil nil nil nil nil nil)
                [:div {:class "container-fluid"}
                [:h2 "Product list"]
                [:table {:style "border-collapse: separate; border-spacing: 10px;"}
                    (for [product product-list]
                        [:tr
                            [:td [:a {:href (str "?product-id=" (:id product))} (:name product)]]
                            [:td [:a {:href (str "?product-id=" (:id product))} (:description product)]]])
                ]]
                (render-html-footer)
            ] ; </div class="container">
        ] ; </body>
    ))

(defn render-chapter-list
    "Render chapter list for selected product."
    [url-prefix title product-id product-name chapter-list]
    (page/xhtml
        (render-html-header url-prefix title)
        [:body
            [:div {:class "container"}
                (render-navigation-bar-section url-prefix title)
                [:div {:class "container-fluid"}
                [:h2 (str "Chapters for product " product-name)]
                [:table {:style "border-collapse: separate; border-spacing: 10px;"}
                    (for [chapter chapter-list]
                        [:tr
                            [:td (:name chapter)]
                            [:td [:a {:href (str "?product-id=" product-id "&chapter-id=" (:id chapter) "&action=rename-chapter")} "rename"]]
                            [:td [:a {:href (str "?product-id=" product-id "&chapter-id=" (:id chapter) "&action=grouplist")} "group list"]]])
                ]]
                (render-html-footer)
            ] ; </div class="container">
        ] ; </body>
    ))

(defn render-group-list
    "Render group list for selected product and chapter."
    [url-prefix title product-id chapter-id product-name chapter-name group-list]
    (page/xhtml
        (render-html-header url-prefix title)
        [:body
            [:div {:class "container"}
                (render-navigation-bar-section url-prefix title)
                [:div {:class "container-fluid"}
                [:h2 (str "Chapters &#x25b6; for product " product-name " and chapter " chapter-name)]
                [:table {:style "border-collapse: separate; border-spacing: 10px;"}
                    (for [group group-list]
                        [:tr
                            [:td (:name group)]
                        ])
                ]]
                (render-html-footer)
            ] ; </div class="container">
        ] ; </body>
    ))

