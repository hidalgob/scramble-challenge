(ns scramble)

(defn send-response [resp]
  {:result (str resp)})

(defn scramble? [pool sample]
  (->> sample
       set
       (map #(>= (get (frequencies pool) % 0)
                 (get (frequencies sample) %)))
       (every? true?)
       (send-response)))