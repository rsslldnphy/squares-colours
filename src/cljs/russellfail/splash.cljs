(ns russellfail.splash)

(defn ripple
  [x y n]
  (into #{} (let [min-x (- x n)
                  min-y (- y n)
                  max-x (+ x n)
                  max-y (+ y n)]
              (concat
                (for [x (range min-x (inc max-x))
                      y [min-y max-y]]
                  [x y])
                (for [y (range min-y (inc max-y))
                      x [min-x max-x]]
                  [x y])))))
