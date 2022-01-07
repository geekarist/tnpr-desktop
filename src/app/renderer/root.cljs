(ns app.renderer.root
  (:refer-clojure :exclude [update])
  (:require [app.renderer.orig-dest :as od]))

(def init {::state.count 0
           ::state.od od/init})

(defn view [state dispatch]
  [:div
   [:div.logos
    [:img.electron {:src "img/electron-logo.png"}]
    [:img.cljs {:src "img/cljs-logo.svg"}]
    [:img.reagent {:src "img/reagent-logo.png"}]]
   [:button
    {:on-click #(dispatch [::msg.inc-counter])}
    (str "Clicked " (state ::state.count) " times")]

   (od/view (state ::state.od)
            (fn [od-msg]
              (dispatch [::msg.od [od-msg]])))])

(defn update [state [msg-key _msg-arg :as message]]
  (condp = msg-key
    
    ::msg.inc-counter
    [(assoc state ::state.count (inc (state ::state.count)))
     nil]

    [state [:effect/log (str "Unknown effect in root: "  message)]]))

(comment
   ;; Work around 'unused var' warnings
  init view update)