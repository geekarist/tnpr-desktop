(ns app.renderer.root)

(def init 0)

(defn view [state dispatch]
  [:div
   [:div.logos
    [:img.electron {:src "img/electron-logo.png"}]
    [:img.cljs {:src "img/cljs-logo.svg"}]
    [:img.reagent {:src "img/reagent-logo.png"}]]
   [:button
    {:on-click #(dispatch :inc-counter)}
    (str "Clicked " state " times")]])

(defn updated [state message]
  (condp = message
    :inc-counter
    [(inc state)
     [:log-effect [:log-effect-arg1 :log-effect-arg2]]]))

(def effects
  {:log-effect
   (fn [arg _dispatch]
     (println arg))})

(comment
   ;; Work around 'unused code' warnings
  init view updated effects
  )