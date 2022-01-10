(ns app.renderer.effects)

(def effects
  {::log
   (fn [arg _dispatch]
     (println arg))})

(defn handle!
  [[effect-key effect-args]
   dispatch]

  (let [apply-effect! (effect-key effects)]
    (apply-effect! effect-args dispatch)))

