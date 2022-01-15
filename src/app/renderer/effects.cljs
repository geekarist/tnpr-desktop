(ns app.renderer.effects
  (:require [ajax.core :refer [ajax-request]]))

(defn- log! [arg _dispatch]
  (println arg))

(defn- http-request! [req _dispatch]
  (println "Request:" req)
  (ajax-request
   (assoc req
          :handler
          (fn [ok? result]
            (println "ok?" ok?
                     ", result:" result)))))

(def effects {::log log!
              ::http-request http-request!})

(defn handle!
  [[effect-key effect-args]
   dispatch]

  (let [apply-effect! (effect-key effects)]
    (apply-effect! effect-args dispatch)))

