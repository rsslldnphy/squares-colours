(ns russellfail.core
  (:require-macros
    [reagent.ratom :refer [reaction]])
  (:require
    [russellfail.handlers]
    [russellfail.subs]
    [russellfail.views]
    [goog.events   :as e]
    [re-frame.core :refer [subscribe dispatch dispatch-sync register-handler register-sub trim-v]]
    [reagent.core  :as reagent]))

(defn ^:export main
  []
  (dispatch-sync [:russellfail.handlers/initialize])
  (reagent/render-component [russellfail.views/page] (.getElementById js/document "app")))
