(ns scramble-challenge.subscription.scramble
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :scramble
  (fn [db]
    (:scramble db)))

(reg-sub
  :scramble/data
  :<- [:scramble]
  (fn [scramble]
    (:data scramble)))
