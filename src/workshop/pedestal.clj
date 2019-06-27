(ns workshop.pedestal
  (:require
    [io.pedestal.http :as http]
    [hiccup.core :refer [html]]))

(defn home-page [request]
  {:status 200
   :body "<p>Home!</p>"})

(defn friendly-page [request]
  {:status 200
   :body (html
           [:h1  {:style {:color "blue"}} "I hear you"]
            [:a {:href "http://clojure.org"} "Clojure - home"]
            [:div "very interesting"])})

(def my-routes
  #{["/friendly" :get `friendly-page]})

(defn start-server []
  (-> {::http/port 8081
       ::http/type :jetty
       ::http/routes my-routes}
    http/create-server
    http/start))

(defn -main [& args]
  (start-server))

(defn boom []
  (try
     (/ 1 0)
     (throw (ex-info "Boom!" {:boom true}))
     (catch Exception e
       (if (:boom (ex-data e))
         (println "There was a boom.")
         (do (println "There was a non-boom.")
             (throw e))))))

(defn capitalize [^String word]
  (if (.isEmpty word)
    word
    (let [up (.toUpperCase (.substring word 0 1))
          down (.toLowerCase (.substring word 1 (count word)))]
      (.concat up down))))

(defn time-capitalize []
  (time (dotimes [i 10000]
          (capitalize "street Knowledge"))))