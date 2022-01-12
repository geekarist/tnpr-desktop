(ns mock
  (:require ["json-server" :as jsrv]))

(defn start! []
  (println "Hello mock")
  (let [server (jsrv/create)
        router (jsrv/router "src/mock-db.json")
        middlewares (jsrv/defaults)]
    (.use server middlewares)
    (.use server router)
    (.listen server 3000 (fn []
                           (println "Mock is running!")))))