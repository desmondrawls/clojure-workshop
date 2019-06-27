(defproject workshop "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [midje "1.9.8"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-jetty-adapter "1.7.1"]
                 [ring/ring-devel "1.7.1"]
                 [compojure "1.6.1"]
                 [hiccup "1.0.5"]
                 [io.pedestal/pedestal.service       "0.5.7"]
                 [io.pedestal/pedestal.jetty         "0.5.7"]
                 [org.clojure/core.async "0.4.500"]
                 [org.clojure/test.check "0.10.0-alpha4"]]
  :repl-options {:init-ns workshop.core}
  :profiles {:dev {:plugins [[lein-midje "2.0.0"]]}}
  :aliases {"test" ["midje"]})