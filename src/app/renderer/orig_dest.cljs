(ns app.renderer.orig-dest)

(def init {::state.orig "Montigny"
           ::state.dest "Paris"})

(defn view [state dispatch]
  [:div.od
   [:input.od-orig
    {:type "text"
     :default-value (state ::state.orig)
     :on-change
     (fn [event]
       (dispatch [::msg.change-orig (-> event .-target .-value)]))}]
   [:input.od-dest
    {:type "text"
     :default-value (state ::state.dest)
     :on-change
     (fn [event]
       (dispatch [::msg.change-dest (-> event .-target .-value)]))}]
   [:button.od-search "Search"]])

(comment init view)