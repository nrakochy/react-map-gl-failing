(ns react-map-gl-failing.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [react-map-gl-failing.events :as events]
   [react-map-gl-failing.views :as views]
   [react-map-gl-failing.config :as config]
   ))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
