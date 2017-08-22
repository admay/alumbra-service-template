(ns leiningen.new.alumbra-service
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "alumbra-service"))

(defn alumbra-service
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' alumbra-service project.")
    (->files data
             ["src/{{sanitized}}/foo.clj" (render "foo.clj" data)])))
