(ns workshop.webapp
  (:require [ring.middleware.reload :refer [wrap-reload]]
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

(defroutes app
  (GET
    "/guess/:number"
    [number]
    (outcome->message (:outcome (f/make-round {:secret 42 :guess (parse-int number)})))))

(def reloadable-app
  (wrap-reload #'app))

(defn -main [& args]
  (jetty/run-jetty reloadable-app {:port 3000}))