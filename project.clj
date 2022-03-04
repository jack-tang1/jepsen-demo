(defproject jepsen.demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :main jepsen.demo.mdb-store-demo
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler jepsen.demo.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]
                        [jepsen "0.2.1-SNAPSHOT"]
                        [verschlimmbesserung "0.1.3"]
                        [clj-http "3.12.3"]
                        [org.clojure/java.jdbc "0.7.12"]]}})
