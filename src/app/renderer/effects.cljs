(ns app.renderer.effects
  (:require [ajax.core :refer [ajax-request]]))

(defn- log! [arg _dispatch]
  (println arg))

(defn- http-request! [[req msg-key :as _effect-args] dispatch]
  (println "Request:" req)
  (ajax-request
   (assoc req
          :handler
          (fn [_ok? result]
            (dispatch [msg-key result])))))

(def effects {::log log!
              ::http-request http-request!})

(defn handle!
  [[effect-key effect-args]
   dispatch]
  (let [apply-effect! (effect-key effects)]
    (if (some? apply-effect!)
      (apply-effect! effect-args dispatch)
      (println "Unknown effect:" effect-key))))

