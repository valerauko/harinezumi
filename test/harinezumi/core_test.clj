(ns harinezumi.core-test
  (:require [clojure.test :refer :all]
            [harinezumi.core]))

(defn throw-info
  []
  (throw (ex-info "Test error"
                  {:cause ::some-error
                   :data {:foo :bar
                          :hoge :fuga}})))

(deftest fail-without-throw
  (is (thrown-ex-data? {[:cause] ::some-error} true)
    "Fails if no exception is thrown"))

(deftest can-test-one-assertion
  (is (thrown-ex-data? {[:cause] ::some-error} (throw-info))
    "Can test top level keys")
  (is (thrown-ex-data? {[:data :foo] :bar} (throw-info))
    "Can test nested keys"))

(deftest can-test-multiple-assertions
  (is (thrown-ex-data? {[:cause] ::some-error
                        [:data :foo] :bar}
        (throw-info))
    "Can test multiple assertions at once")
  (testing "Fails (and stops checking) at the first failed assertion"
    (is (thrown-ex-data? {[:cause] ::unexpected-error
                          [:data :foo] :bar}
          (throw-info))
      "Should fail")
    (is (thrown-ex-data? {[:cause] ::some-error
                          [:data :foo] :baz}
          (throw-info))
      "Should fail")))
