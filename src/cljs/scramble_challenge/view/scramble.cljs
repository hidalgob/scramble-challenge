(ns scramble-challenge.view.scramble
  (:require [re-frame.core :refer [dispatch subscribe]]
            [reagent.core :as r]))

(defn disabled-button? [pool sample]
  (or (empty? pool)
      (empty? sample)))

(defn scramble-view []
  (let [pool     (r/atom "")
        sample   (r/atom "")
        scramble (subscribe [:scramble/data])]
    (fn []
      [:section {:class "section"}
       [:div {:class "container"}
        [:div {:class "content"}
         [:h1 "Scramble!"]
         [:div {:class "inputs"}
          [:div
           [:label "Pool: "]
           [:input {:name        "pool"
                    :placeholder "Input pool"
                    :type        :text
                    :value       @pool
                    :on-change   #(reset! pool (-> % .-target .-value))}]]
          [:div
           [:label "Sample: "]
           [:input {:name        "sample"
                    :placeholder "Input sample"
                    :type        :text
                    :value       @sample
                    :on-change   #(reset! sample (-> % .-target .-value))}]]
          [:button {:type     :submit
                    :on-click #(dispatch [:scramble/run @pool @sample])
                    :disabled (disabled-button? @pool @sample)}
           "Check Scramble"]]
         (when (not-empty @scramble)
           [:div {:class "result"}
            [:div
             [:label "Result: " (:result @scramble)]]])]]])))