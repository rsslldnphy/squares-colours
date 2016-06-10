(ns russellfail.hex)

(def hex-digits
  (concat (range (int \0)
                 (inc (int \9)))
          (range (int \a)
                 (inc (int \f)))))

(defn rand-hex
  []
  (apply str "#"
         (for [_ (range 6)] (rand-nth hex-digits))))

