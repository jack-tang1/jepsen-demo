(ns jepsen.demo.mdb-store-demo
  (:require [clojure.tools.logging :refer :all]
            [clojure.string :as str]
            [jepsen [cli :as cli]
             [control :as c]
             [db :as db]
             [tests :as tests]]
            [jepsen.control.util :as cu]
            [jepsen.os.debian :as debian]
            [jepsen.db :as db]))


(defn db
  [version]
  (reify db/DB
    (setup! [_ test node]
      (info node "installing mdb store " version))
    (teardown! [_ test node]
      (info node "tear down mdb store"))))

(defn mdb-store-test [opts]
  (merge tests/noop-test opts
         {:node "192.168.3.90"
          :name "mdb-store"
          :db (db "v1.0.0")
          :pure-generators true}))

(defn -main [& args]
  (cli/run! (cli/single-test-cmd {:test-fn mdb-store-test})
            args))
