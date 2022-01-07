(ns app.renderer.orig-dest)

(defn view []
  [:div.od
   [:input.od-orig {:type "text"}]
   [:input.od-dest {:type "text"}]
   [:button.od-search "Search"]])