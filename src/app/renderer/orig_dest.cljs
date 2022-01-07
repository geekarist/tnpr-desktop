(ns app.renderer.orig-dest
  (:refer-clojure :exclude [update]))

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

(defn update [state [msg-key msg-arg :as msg]]
  (condp = msg-key
    
    ::msg.change-orig
    [(assoc state ::state.orig msg-arg)
     nil]
    
    ::msg.change-dest
    [(assoc state ::state.dest msg-arg)
     nil]
    
    [state [:effect/log (str "Unknown message in orig_dest: " msg)]]))

(comment init view update)