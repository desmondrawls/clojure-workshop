(ns workshop.webapp
  (:require [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.params :as params]
            [ring.middleware.keyword-params :as keyword-params]
            [compojure.core :refer [GET defroutes]]
            [ring.adapter.jetty :as jetty]
            [workshop.factorial :as f]
            [compojure.core :refer [GET defroutes]]))


(defn parse-int [number-string]
  (try (Integer/parseInt number-string)
       (catch Exception e nil)))

(defn outcome->message [outcome]
  (outcome {:invalid "Sorry that is an invalid entry."
            :win "Correct. You win!"
            :too-low "Too low."
            :too-high "Too high."}))

(defn noisy-middleware [handler]
  (fn [request]
    (println (str "Request: " request))))

(defn my-handler [request]
  {:status 200
   :body (str "<pre>"
           (:query-string request)
           "</pre>"
           "<pre>"
           (pr-str (:params request))
           "</pre")})

(defn guess [number]
  {:status 200
   :body (outcome->message (:outcome (f/make-round {:secret 42 :guess (parse-int number)})))})

(defroutes routes
  (GET
    "/guess/:number"
    [number]
    (guess number)))

(def reloadable-routes
  (wrap-reload #'routes))

(def my-app
  (->
    reloadable-routes
    keyword-params/wrap-keyword-params
    params/wrap-params))

(defn -main [& args]
  (jetty/run-jetty #'my-app {:port 3000 :join? false}))