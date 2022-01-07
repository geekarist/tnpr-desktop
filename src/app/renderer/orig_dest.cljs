(ns app.renderer.orig-dest)

(def init {::state.orig "Montigny"
           ::state.dest "Paris"})

(defn view [state]
  [:div.od
   [:input.od-orig {:type "text" :value (state ::state.orig)}]
   [:input.od-dest {:type "text" :value (state ::state.dest)}]
   [:button.od-search "Search"]])

(comment init view)