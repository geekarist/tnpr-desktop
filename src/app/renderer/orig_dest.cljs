(ns app.renderer.orig-dest
  (:refer-clojure :exclude [update])
  (:require [app.renderer.effects :as fx]
            [ajax.core :refer [json-request-format json-response-format]]))

(def init {::state.orig "Montigny"
           ::state.dest "Paris"})

(defn view [state dispatch-to-od]
  [:div.od
   [:input.od-orig
    {:type "text"
     :default-value (state ::state.orig)
     :on-change
     (fn [event]
       (dispatch-to-od [::msg.change-orig (-> event .-target .-value)]))}]
   [:input.od-dest
    {:type "text"
     :default-value (state ::state.dest)
     :on-change
     (fn [event]
       (dispatch-to-od [::msg.change-dest (-> event .-target .-value)]))}]
   [:button.od-search {:on-click #(dispatch-to-od [::msg.submit])} "Search" ]])

(defn- update-on-submit [state]
  (let [effect-key ::fx/http-request
        effect-arg {:uri (str "http://localhost:3000/journeys"
                              "?request.from=2.3749036;48.8467927"
                              "&request.to=2.2922926;48.8583736")
                    :method :get
                    :format (json-request-format)
                    :response-format (json-response-format)}
        effect-target ::msg.http-resp-received] 
    [state
     [effect-key [effect-arg effect-target]]]))

(defn- update-on-change-dest [state msg-arg]
  [(assoc state ::state.dest msg-arg)
     nil])

(defn- update-on-change-orig [state msg-arg]
  [(assoc state ::state.orig msg-arg)
     nil])

(defn- update-default [state msg]
  [state [::fx/log (str "Unknown message in orig_dest: " msg)]])

(defn update [state [msg-key msg-arg :as msg]]
  (condp = msg-key
    ::msg.change-orig (update-on-change-orig state msg-arg)
    ::msg.change-dest (update-on-change-dest state msg-arg)
    ::msg.submit (update-on-submit state)
    (update-default state msg)))

(comment init view update)