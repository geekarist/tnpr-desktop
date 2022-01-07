(ns app.renderer.runtime
  (:require [reagent.core :refer [atom]]
            [reagent.dom :as rd]
            [app.renderer.root :as root]))

(enable-console-print!)

(defonce state (atom root/init))

(def effects
  {:effect/log
   (fn log! [arg _dispatch]
     (println arg))})

(defn- handle-effect!
  [effects
   [effect-key effect-args]
   dispatch]
  
  (let [apply-effect! (effect-key effects)]
    (apply-effect! effect-args dispatch)))

(defn- root-component []
  (root/view
   @state
   (fn dispatch [message]
     (let [[new-state new-effect] (root/update @state message)]
       (compare-and-set! state @state new-state)
       (handle-effect! effects new-effect dispatch)))))

(defn ^:dev/after-load start! []
  (rd/render
   [root-component]
   (js/document.getElementById "app-container")))

(comment
  start! ;; Work around 'unused var' warning
  )