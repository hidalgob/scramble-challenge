(ns scramble)

(defn scramble? [pool sample]
  (->> sample
       set
       (map #(>= (get (frequencies pool) % 0)
                 (get (frequencies sample) %)))
       (every? true?)))