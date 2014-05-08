(ns recorder
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]))

(defn recorder-button [data owner]
  (reify
    om/IRenderState
    (render-state [this {:keys [record]}]
      (dom/div #js {:className "col-md-12" :style #js {:textAlign "center"}}
        (dom/button #js {
            :className "btn btn-lg btn-primary"
            :onClick (fn [e] (put! record []))
          } (:text data))))))

(def app-state
  (atom
    {:text "Record!"}))

(let [record-chan (chan)]
  (go (loop []
    (let [_ (<! record-chan)]
      (js/alert "fuck yeah channels!")
      (recur))))
  (om/root
    (fn [app-state owner]
      (cond
       (aget js/navigator "geolocation") (om/build recorder-button app-state {
         :init-state {:record record-chan}})
       :else (dom/h1 nil #js {:text "Please use a browser that supports geolocation"})))
    app-state
    {:target (. js/document (getElementById "my-app"))}))
