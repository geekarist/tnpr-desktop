(ns app.renderer.root)

(def init 0)

(defn view [state dispatch]
  [:div
   [:div.logos
    [:img.electron {:src "img/electron-logo.png"}]
    [:img.cljs {:src "img/cljs-logo.svg"}]
    [:img.reagent {:src "img/reagent-logo.png"}]]
   [:button
    {:on-click #(dispatch :msg/inc-counter)}
    (str "Clicked " state " times")]])

(defn updated [state message]
  (condp = message
    :msg/inc-counter
    [(inc state)
     [:effect/log [:effect/log-arg1 :effect/log-arg2]]]))

(def effects
  {:effect/log
   (fn [arg _dispatch]
     (println arg))})

(comment
   ;; Work around 'unused var' warnings
  init view updated effects
  )