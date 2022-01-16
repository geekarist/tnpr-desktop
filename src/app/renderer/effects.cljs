(ns app.renderer.effects
  (:require [ajax.core :refer [ajax-request]]))

(defn- log! [arg _dispatch]
  (println arg))

(defn- http-request!
  [[req msg-key :as _effect-args] dispatch-to-root]
  (ajax-request
   (assoc req
          :handler
          (fn [_ok? result]
            (dispatch-to-root [msg-key result])))))

(def effects {::log log!
              ::http-request http-request!})

(defn handle!
  [[effect-key effect-args]
   dispatch-to-root]
  (let [apply-effect! (effect-key effects)]
    (if (some? apply-effect!)
      (apply-effect! effect-args dispatch-to-root)
      (println "Unknown effect:" effect-key))))

