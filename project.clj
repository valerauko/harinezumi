(defproject net.valerauko/harinezumi "1.1.0"
  :description "Clojure test helper to check ex-info details"
  :url "https://github.com/valerauko/harinezumi"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :scm {:name "git"
        :url "https://github.com/valerauko/harinezumi"
        :tag "v1.1.0"}
  :dependencies []
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.12.0"]
                                  [org.clojure/tools.namespace "1.5.0"]
                                  [lambdaisland/kaocha "1.91.1392"]
                                  [clj-kondo "2025.01.16"]]
                   :plugins [[lein-ancient "0.7.0"
                              :exclusions [org.clojure/clojure]]]
                   :aliases {"lint" ["run" "-m" "clj-kondo.main"
                                     "--config" ".clj-kondo/config.edn"
                                     "--lint" "src" "test"]
                             "test" ["run" "-m" "kaocha.runner"]}}
             :clj1.11 {:dependencies
                       [[org.clojure/clojure "1.11.4"]]}
             :clj1.12 {:dependencies
                       [[org.clojure/clojure "1.12.0"]]}})
