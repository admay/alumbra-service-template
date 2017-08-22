(defproject {{name}} "0.1.0-SNAPSHOT"
  :description
  :url
  :license

  :dependencies [[org.clojure/clojure "1.9.0-alpha16"]
                 [org.clojure/spec.alpha "0.1.123"]
                 [org.clojure/java.jdbc "0.7.0"]

                 [alumbra "0.2.6"]
                 [environ.core "1.1.0"]]

  :plugins [[lein-environ "1.1.0"]]

  :main ^:skip-aot {{name}}.core
  :source-paths ["src"]
  :resource-paths ["resources"]
  :clean-targets [:target-path :compile-path "logs"]
  :target-path "target/")

