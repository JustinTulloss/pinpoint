(defproject pinpoint "0.0.0"
  :plugins [[lein-cljsbuild "1.0.3"]]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [om "0.6.2"]]
  :cljsbuild {
    :builds [{
             :source-paths ["src"]
             :compiler {
               :output-to "out/main.js"
               :output-dir "out"
               :optimizations :none
               :source-map true}}]})
