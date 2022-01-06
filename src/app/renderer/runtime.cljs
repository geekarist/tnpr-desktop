(ns app.renderer.runtime
  (:require [reagent.core :refer [atom]]
            [reagent.dom :as rd]
            [app.renderer.root :as root]))

(enable-console-print!)

(defonce state (atom root/init))

(defn root-component []
  (root/view @state
             (fn dispatch [message]
               (swap! state root/updated message))))

(defn ^:dev/after-load start! []
  (rd/render
   [root-component]
   (js/document.getElementById "app-container")))

(comment
  start! ;; Work around 'unused var' warning
  )