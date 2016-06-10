(ns scratch)

(def origin
  [3 3])

(def ripple-1
  [origin])

(def ripple-2
  (let [[x y] origin]
    (for [x [(dec x) x (inc x)]
          y [(dec y) y (inc y)]]
      [x y])))

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
                      x [min-x max-y]]
                  [x y])))))


(ripple 3 3 1)

#{[4 3] [2 2] [2 3]
  [3 4]       [4 2]
  [2 4] [4 4] [3 2]}




(defn ripple
  [x y n]
  (println "x:" x "y:" y "n:" n)
  (into #{} (let [min-x (- x n)
                  min-y (- y n)
                  max-x (+ x n)
                  max-y (+ y n)]
              (println min-x min-y max-x max-y)
              (concat
                (for [x (range min-x (inc max-x))
                      y [min-y max-y]]
                  [x y])
                (for [y (range min-y (inc max-y))
                      x [min-x max-x]]
                  [x y])))))

(ripple 8 2 0)
