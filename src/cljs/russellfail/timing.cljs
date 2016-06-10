(ns russellfail.timing)

(defn start-random-timer
  [handler]
  (js/setTimeout #(do (handler)
                      (start-random-timer handler))
                 (rand-int 50000)))
