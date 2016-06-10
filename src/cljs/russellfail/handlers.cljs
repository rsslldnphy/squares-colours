(ns russellfail.handlers
  (:require
    [russellfail.hex    :as hex]
    [russellfail.splash :as splash]
    [re-frame.core      :refer [dispatch dispatch-sync register-handler trim-v]]
    [reagent.ratom      :refer [IReactiveAtom]]))

(defn bang!
  "Middleware that allows you to do side-effecty things,
  but not to change the value of app-db. You get a dereffed copy of app-db
  but any return value is ignored."
  [handler!]
  (fn bang-handler
    [app-db event-vec]
    (if (satisfies? IReactiveAtom app-db)
      (handler! @app-db event-vec )
      (handler! app-db event-vec))
    app-db))

(register-handler
  ::initialize
  [bang!]
  (fn [db _]
    (let [height (.-innerHeight js/window)
          width  (.-innerWidth js/window)]
      (dispatch-sync [::update-window-size height width])
      (set! (.-onresize js/window)
        #(dispatch [::update-window-size (.-innerHeight (.-target %)) (.-innerWidth (.-target %))])))))

(register-handler
  ::update-window-size
  [trim-v]
  (fn [db [height width]]
    (assoc db :height height :width width)))

(register-handler
  ::update-block-color
  [trim-v]
  (fn [db [x y color]]
    (assoc-in db [:blocks y x :color] (or color (hex/rand-hex)))))


(register-handler
  ::splash
  [trim-v bang!]
  (fn [db [x y n ripples]]
    (let [n       (or n 0)
          ripples (or ripples 1)]
      (when (not-empty
              (doall (for [[x y] (splash/ripple x y n)
                           :when (get-in db [:blocks y x])]
                       (do (dispatch [::update-block-color x y "transparent"]) [x y]))))
        (js/setTimeout #(dispatch [::splash x y (inc n) ripples]) 200))
      (when (and (= n 2)
                 (pos? ripples))
        (dispatch [::ripple x y 0 ripples])))))

(register-handler
  ::ripple
  [trim-v bang!]
  (fn [db [x y n ripples]]
    (when (not-empty
            (doall (for [[x y] (splash/ripple x y n)
                         :when (get-in db [:blocks y x])]
                     (do (dispatch [::update-block-color x y]) [x y]))))
      (js/setTimeout #(dispatch [::ripple x y (inc n) ripples]) 200))
    (when (and (= n 2)
               (pos? ripples))
      (dispatch [::splash x y 0 (dec ripples)]))))

