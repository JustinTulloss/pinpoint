(defproject pinpoint "0.0.0"
  :plugins [[lein-cljsbuild "1.0.3"]]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/core.async "0.1.301.0-deb34a-alpha"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [om "0.6.2"]]
  :cljsbuild {
    :builds [{:id "dev"
             :source-paths ["src"]
             :compiler {
               :output-to "out/main.js"
               :output-dir "out"
               :optimizations :none
               :source-map true}}
             {:id "release"
              :source-paths ["src"]
              :compiler {
                :output-to "release/main.js"
                :output-dir "release"
                :pretty-print false
                :optimizations :advanced
                :preamble ["react/react.min.js"]
                :externs ["react/externs/react.js"]}}]})
