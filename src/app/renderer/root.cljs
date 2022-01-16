(ns app.renderer.root
  (:refer-clojure :exclude [update])
  (:require [app.renderer.orig-dest :as od]
            [app.renderer.effects :as fx]))

(def init {::state.count 0
           ::state.od od/init})

(defn view [state dispatch-to-root]
  [:div
   [:div.logos
    [:img.electron {:src "img/electron-logo.png"}]
    [:img.cljs {:src "img/cljs-logo.svg"}]
    [:img.reagent {:src "img/reagent-logo.png"}]]
   [:button
    {:on-click #(dispatch-to-root [::msg.inc-counter])}
    (str "Clicked " (state ::state.count) " times")]

   (od/view (state ::state.od)
            (fn dispatch-to-od [od-msg]
              (dispatch-to-root [::msg.od od-msg])))])

(defn update [state [msg-key msg-arg :as message]]
  (condp = msg-key
    
    ::msg.inc-counter
    [(assoc state ::state.count (inc (state ::state.count)))
     nil]
    
    ::msg.od
    (let [od-old-state (state ::state.od)
          od-update-result (od/update od-old-state msg-arg)
          [od-new-state od-new-effect] od-update-result
          new-state (assoc state ::state.od od-new-state)
          new-effect od-new-effect]
      [new-state new-effect])

    [state [::fx/log (str "Unknown message in root: "  message)]]))

(comment
   ;; Work around 'unused var' warnings
  init view update)