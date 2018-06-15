(ns react-map-gl-failing.views
  (:require
   [re-frame.core :as re-frame]
   [react-map-gl-failing.subs :as subs]
   [react-map-gl :as M]
   ))


(js/console.log M)

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 "Hello from " @name]
     ]))
