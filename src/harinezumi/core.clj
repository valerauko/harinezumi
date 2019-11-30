(ns harinezumi.core
  (:require [clojure.test :refer [assert-expr do-report]]))

(defmethod assert-expr 'thrown-ex-data?
  ^{:doc "Assert the presence of details of ex-data map keys."}
  assert-exinfo-details
  [msg [_ checks & body :as form]]
  `(try
     ~@body
     (do-report {:type :fail
                 :message ~msg
                 :expected (list '~'ex-data RuntimeException)
                 :actual nil})
     (catch RuntimeException e#
       (let [info# (ex-data e#)
             pass# {:type :pass
                    :message ~msg
                    :expected '~form
                    :actual info#}]
         (->> ~checks
              (reduce
                (fn [_# [coords# value#]]
                  (let [actual# (get-in info# coords#)]
                    (if (not= actual# value#)
                      (reduced {:type :fail
                                :message ~msg
                                :expected (list '~'= (list '~'get-in info# coords#) value#)
                                :actual (list '~'not= actual# value#)})
                      pass#)))
                pass#)
              do-report)
         info#))))
