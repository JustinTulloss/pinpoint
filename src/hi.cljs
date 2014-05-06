(ns hi)
(enable-console-print!)
(defn ^:export greet [n]
  (println (str "Hello " n)))
