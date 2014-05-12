(ns recorder
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]))

(enable-console-print!)

(defn recorder-button [app owner]
  (defn do-record [recorder]
    (om/set-state! owner :text "Recording\u2026")
    (.getCurrentPosition (.. js/navigator -geolocation) (fn [position]
      ; XXX: There should be some way to just revert to the old state here...
      (om/set-state! owner :text "Record!")
      (put! recorder position))))
  (reify
    m/IDisplayName
    (display-name [_] "RecorderButton")
    om/IInitState
    (init-state [_]
      {:text "Record!"})
    om/IRenderState
    (render-state [_ {:keys [recorder text]}]
      (dom/div #js {:className "col-md-12" :style #js {:textAlign "center"}}
        (dom/button #js {
            :className "btn btn-lg btn-primary"
            :onClick #(do-record recorder)
          } text)))))

(let [record-chan (chan)]
  (go (loop []
    (let [position (<! record-chan)]
      (.log js/console (.stringify js/JSON position))
      (recur))))
  (om/root
    (fn [app-state owner]
      (cond
       (. js/navigator -geolocation) (om/build recorder-button nil {
         :state {:recorder record-chan}})
       :else (dom/h1 nil "Please use a browser that supports geolocation")))
    nil
    {:target (. js/document (getElementById "my-app"))}))

