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
    (inc state)))

(comment
  init view updated
  )