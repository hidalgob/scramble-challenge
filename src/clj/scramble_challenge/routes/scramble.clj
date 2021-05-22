(ns scramble-challenge.routes.scramble
  (:require [scramble :as scramble]
            [scramble-challenge.middleware :as middleware]
            [ring.util.http-response :as response]
            [scramble-challenge.layout :as layout]))

(defn home-page [request]
  (layout/render request "home.html"))

(defn scramble-routes []
  ["/api/scramble"
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/:pool/:sample" {:get {:handler (fn [{:keys [path-params]}]
                                       (let [scramble (scramble/scramble? (:pool path-params) (:sample path-params))]
                                         {:status 200
                                          :body   scramble}))}}]])
