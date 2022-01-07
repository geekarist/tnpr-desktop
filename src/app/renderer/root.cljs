(ns app.renderer.root
  (:refer-clojure :exclude [update])
  (:require [app.renderer.orig-dest :as od]))

(def init {::state.count 0})

(defn view [state dispatch]
  [:div
   [:div.logos
    [:img.electron {:src "img/electron-logo.png"}]
    [:img.cljs {:src "img/cljs-logo.svg"}]
    [:img.reagent {:src "img/reagent-logo.png"}]]
   [:button
    {:on-click #(dispatch :msg/inc-counter)}
    (str "Clicked " (state ::state.count) " times")]
   
   (od/view)])

(defn update [state message]
  (condp = message
    :msg/inc-counter
    [(assoc state ::state.count (inc (state ::state.count)))
     [:effect/log [:effect/log-arg1 :effect/log-arg2]]]))

(comment
   ;; Work around 'unused var' warnings
  init view update
  )