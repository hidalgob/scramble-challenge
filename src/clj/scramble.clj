(ns scramble
  "Core namespace for the scramble function")


(defn valid-values?
  "Check for valid values in scramble function.
  Returns true if pool and sample are valid values and within limits, otherwise returns false"
  [pool sample]
  (and (>= 100 (count pool) 1)
       (>= 100 (count sample) 1)
       (boolean (re-matches #"^[a-z\-]+$" pool))
       (boolean (re-matches #"^[a-z\-]+$" sample))))

(defn scramble?
  "Check if a portion of Pool can be rearranged to match Sample.
  Returns true if valid and can be rearranged, otherwise returns false."
  [pool sample]
  (if (valid-values? pool sample)
    (->> sample
         set
         (map #(>= (get (frequencies pool) % 0)
                   (get (frequencies sample) %)))
         (every? true?))
    false))
