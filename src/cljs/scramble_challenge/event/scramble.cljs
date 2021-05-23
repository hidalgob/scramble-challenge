(ns scramble-challenge.event.scramble
  (:require [ajax.core :refer [json-response-format]]
            [day8.re-frame.http-fx]
            [re-frame.core :refer [reg-event-db reg-event-fx]]))

(reg-event-fx
  :scramble/run
  (fn [{:keys [db]} [_ pool sample]]
    {:db         (-> db
                     (assoc :loading true))
     :http-xhrio {:method          :get
                  :uri             (str "/api/scramble/" pool "/" sample)
                  :response-format (json-response-format {:keywords? true})
                  :on-success      [:scramble/success]
                  :on-failure      [:scramble/failure]}}))

(reg-event-db
  :scramble/success
  (fn [db [_ result]]
    (println db result)
    (-> db
        (assoc :loading false)
        (assoc-in [:scramble :data] result))))

(reg-event-fx
  :scramble/failure
  (fn [_ [_ error]]
    {:dispatch [:common/set-error (:status-text error)]}))
