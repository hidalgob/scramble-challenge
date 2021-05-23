(ns scramble-challenge.view.scramble
  (:require [re-frame.core :refer [dispatch subscribe]]
            [reagent.core :as r]))

(defn only-alpha? [str]
  (boolean (re-matches #"^[a-z\-]+$" str)))

(defn disabled-button? [pool sample]
  (or (not (only-alpha? pool))
      (not (only-alpha? sample))))

(defn scramble-view []
  (let [pool     (r/atom "")
        sample   (r/atom "")
        scramble (subscribe [:scramble/data])]
    (fn []
      [:section {:class "section"}
       [:div {:class "container"}
        [:div {:class "content"}
         [:h1 "Scramble challenge"]
         [:p "Returns true if a portion of Pool characters can be rearranged to match Sample, otherwise returns false"]
         [:p [:b "Only accepts lower-case letters. [a-z]"]]
         [:p "Example:"]
         [:p "Pool: \"rekqodlw\""]
         [:p "Sample: \"world\""]
         [:p "Result: True"]
         [:div {:class "inputs"}
          [:div
           [:label "Pool: "]
           [:input {:name        "pool"
                    :type        :text
                    :value       @pool
                    :pattern     "[a-z]+"
                    :on-change   #(reset! pool (-> % .-target .-value))}]]
          [:div
           [:label "Sample: "]
           [:input {:name        "sample"
                    :type        :text
                    :value       @sample
                    :pattern     "[a-z]+"
                    :on-change   #(reset! sample (-> % .-target .-value))}]]
          [:button {:type     :submit
                    :on-click #(dispatch [:scramble/run @pool @sample])
                    :disabled (disabled-button? @pool @sample)}
           "Check Scramble"]]
         (only-alpha? @pool)
         (when (not-empty @scramble)
           [:div {:class "result"}
            [:div
             [:label "Result: " (str (:result @scramble))]]])]]])))