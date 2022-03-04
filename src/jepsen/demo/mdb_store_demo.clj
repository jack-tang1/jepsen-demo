(ns jepsen.demo.mdb-store-demo
  (:require [jepsen.cli :as cli]
            [jepsen.tests :as tests]))

(defn mdb-store-test [opts]
  merge tests/noop-test {:pure-generators true :name "mdb-store"} opts)

(defn -main [& args]
  (cli/run! (cli/single-test-cmd {:test-fn mdb-store-test})
            args))
