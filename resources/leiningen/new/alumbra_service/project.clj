(defproject {{name}} "0.1.0-SNAPSHOT"
  :description
  :url
  :license

  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [alumbra ""]
                 ]

  :main ^:skip-aot {{name}}.core
  :source-paths ["src"]
  :resource-paths ["resources"]
  :clean-targets [:target-path :compile-path "logs"]
  :target-path "target/"

  :profiles {})

