(ns jepsen.demo.main
  (:gen-class)
  (:require [clojure.java.io]
            [clj-http.client :as client]
            [clojure.set :as set]))

;(defn example []
  ;(.exists (file "Example.txt")))

(def a-map {:a 1, :b 2, :c 3})

(defn hello
  []
  (println (str "hello" " world"))
  (println (+ 1 2))
  (println (inc 5))
  (println (dec 4))
  (println (max 1, 2, 3, 5))
  (println (min 1, 2, 34, 5))
  (println (rem 5 2))
  (println a-map)
  (println (and 1 2)))

(defn while-loop []
  (def x (atom 1))
  (while (< @x 5)
    (do (println @x
                 (swap! x inc)))))

(defn doseq-loop []
  (doseq [n [0, 1, 2]]
    (println n)))

(defn dotimes-loop []
  (dotimes [n 4]
    (if (not= n 2)
      (println n))))

(defn case-con []
  (def n 5)
  (case n 5 (println "x is 5")
          10 (println "x is 10")
          (println "x is not 5 or 10")))

(defn condition []
  (def a 5)
  (def b 10)
  (cond
    (= a 5) (println "a is 5")
    (= b 10) (println "b is 10")
    :else (println "a is not 5,b is not 10")))

(defn varFun [message & others]
  (println (filter even? (range 0 10)))
  (println (str message (clojure.string/join " " others))))

(defn readFile1 []
  (def s1 (slurp "example.txt"))
  (println s1))

(defn readFile2 []
  (with-open [rdr (clojure.java.io/reader "example.txt")]
    (reduce conj [] (line-seq rdr))))

(defn writeFile1 []
  (spit "example1.txt" "this is a string"))

(defn writeFile2 []
  (with-open [w (clojure.java.io/writer "example2.txt" :append true)]
    (.write w (str "hello" "world"))))

(defn strDemo []
  (println (clojure.string/lower-case "Heelo"))
  (println (clojure.string/upper-case "hello"))
  (println (clojure.string/join "," ["hello" "world"]))
  (def words (clojure.string/split "hello world" #" "))
  (doseq [word words] (println word))
  (println (clojure.string/reverse "hello world"))
  (println (clojure.string/replace "hello" "ll" "aa")))

(defn listDemo []
  (def x (list* 1 [2, 3, 4]))
  (doseq [l x] (println l))
  (println (first x))
  (println (nth x 2))
  (println (rest x)))

(defn setDemo []
  (def s (set '(1, 2, 3, 1, 2)))
  (println s)
  (println (sorted-set 2, 1, 5, 1))
  (println (get s 2))
  (println (contains? s 4))
  (println (conj s 4))
  (println (disj s 3))
  (println (set/union s (conj s 12))))

(defn httpDemo []
  (println (client/get "https://www.baidu.com")))

(defn mapDemo []
  (def h-map (hash-map "a" "1" "b" "2" "c" "3"))
  (def s-map (sorted-map "q" "3" "w" "4" "e" "5"))
  (println (get h-map "a"))
  (println (find h-map "a"))
  (println (keys h-map))
  (println (vals h-map))
  (println (dissoc h-map "a"))
  (println (merge h-map s-map)))

(defn exceptionDemo []
  (try
    (aget (int-array [1 2 3]) 5)
    (catch Exception e (println (str "caught exception: " (.toString e))))
    (finally (println "this is our final block"))))

(defn seqDemo []
  (def s [1 2 3])
  (println s)
  (println (cons 0 s))
  (println (conj s 5))
  (println (reverse s)))

(defn dateDemo []
  (def date0 (.toString (java.util.Date.)))
  (println date0)
  (def date1 (.format (java.text.SimpleDateFormat. "yyyy-MM-dd HH:mm:ss") (new java.util.Date)))
  (println date1)
  (def date2 (.getTime (java.util.Date.)))
  (println date2))

(defn atomicDemo []
  (def a (atom 1))
  (println @a)
  (reset! a 2)
  (println @a)
  (compare-and-set! a 2 3)
  (println @a)
  (swap! a dec)
  (println @a))

(defn structDemo []
  (defstruct Employee :EmployeeName :Employeeid)
  (def emp0 (struct Employee "jack" 1))
  (def emp1 (struct-map Employee :EmployeeName "Bob"))
  (println emp0 emp1)
  (println (:EmployeeName emp0))
  (def emp2 (assoc emp1 :Employeeid 2 :EmployeeRank "A"))
  (println emp2))

(defn agentDemo []
  (def counter (agent 0))
  (println @counter)
  (send counter + 100)
  (println @counter)
  (shutdown-agents))

(defn -main
  [& arglist]
  (agentDemo))