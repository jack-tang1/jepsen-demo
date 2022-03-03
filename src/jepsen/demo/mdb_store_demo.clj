(ns jepsen.demo.mdb-store-demo
  (:require [clojure.tools.logging :refer :all]
            [clojure.string :as str]
            [jepsen [cli :as cli]
             [control :as c]
             [db :as db]
             [tests :as tests]]
            [jepsen.control.util :as cu]
            [jepsen.os.debian :as debian]))

(defn db
  "mdb-store db for a particular version"
  [version]
  (reify db/DB
    (setup! [_ test node]
      (info node "installing mdb-store" version))
    (teardown! [_ test node]
      (info node "tearing down mdb-store"))))
