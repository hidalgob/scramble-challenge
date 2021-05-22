(ns scramble-challenge.event.scramble
  (:require [ajax.core :refer [json-response-format]]
            [day8.re-frame.http-fx]
            [re-frame.core :refer [reg-event-db reg-event-fx]]))


(reg-event-db
  :scramble/run
  (fn [a pool sample]
    (println a pool sample)
    {:http-xhrio {:method          :get
                  :uri             (str "/api/scramble/" (:pool pool) "/" (:sample sample))
                  :response-format (json-response-format {:keywords? true})
                  :on-success      [:scramble/success]
                  :on-failure      [:scramble/failure]}}))

(reg-event-db
  :scramble/success
  (fn [a b]
    (println a b)
    (assoc-in [:scramble :data] :data b)))

(reg-event-fx
  :scramble/failure
  (fn [a [b error]]
    (println a b error)
    {:dispatch [:common/set-error (:status-text error)]}))