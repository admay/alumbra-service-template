(ns leiningen.new.alumbra-service
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "alumbra-service"))

(defn alumbra-service
  [name & args]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' alumbra-service project.")
    (->files data

             ;; root
             ["README.md" (render "README.md" data)]
             ["project.clj" (render "project.clj" data)]
             ["profiles.clj" (render "profiles.clj" data)]
             [".gitignore" (render ".gitignore" data)]

             ;; src
             ["src/{{sanitized}}/database.clj" (render "database.clj" data)]
             ["src/{{sanitized}}/resolvables.clj" (render "resolvables.clj" data)]
             ["src/{{sanitized}}/root.clj" (render "root.clj" data)]
             ["src/{{sanitized}}/server.clj" (render "server.clj" data)]
             ["src/{{sanitized}}/specs.clj" (render "specs.clj" data)]
             ["src/{{sanitized}}/utils.clj" (render "utils.clj" data)]

             ;; test

             ;; resources

             )))
