(ns russellfail.views
  (:require-macros
    [russellfail.views :refer [with-subs]])
  (:require
    [russellfail.timing :as timing]
    [goog.events   :as e]
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [reagent.core  :as reagent]))

(defn block
  [x y]
  (timing/start-random-timer #(dispatch [:russellfail.handlers/update-block-color x y]))
  (with-subs [size  [:russellfail.subs/block-size]
              color [:russellfail.subs/block-color x y]]
    [:div.block {:style {:width (str size "px")
                         :height (str size "px")
                         :background-color color}
                 :on-mouse-leave
                 #(dispatch [:russellfail.handlers/update-block-color x y])
                 :on-click
                 #(dispatch [:russellfail.handlers/splash x y])}]))

(defn page
  []
  (with-subs [columns [:russellfail.subs/columns]
              rows    [:russellfail.subs/rows]]
    [:div.overlays-container
     (into [:div.blocks-overlay]
           (for [y (range rows)
                 x (range columns)]
             [block x y]))
     [:div.heading-overlay [:h1 "rsslldnphy.com"]]]))
