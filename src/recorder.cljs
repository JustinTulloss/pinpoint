(ns recorder
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(defn recorder-button [data owner]
  (reify
    om/IRender
    (render [this]
      (dom/div #js {:className "col-md-12" :style #js {:textAlign "center"}}
        (dom/button #js {:className "btn btn-lg btn-primary"} (:text data))))))

(def app-state
  (atom
    {:text "Record!"}))

(om/root
  recorder-button
  app-state
  {:target (. js/document (getElementById "my-app"))})
