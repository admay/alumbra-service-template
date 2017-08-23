(ns {{name}}.server
    (:require
     [clojure.java.io :as io]
     [compojure.core :refer :all]
     [compojure.route :as route]
     [compojure.handler :as handler]
     [alumbra.core :as alumbra]
     [alumbra.web.graphiql-workspace :as graphiql]
     [{{name}}.root :refer [QueryRoot MutationRoot]]))

(def graphql-handler
  (alumbra/handler
   {:schema (io/resource "schema.graphql")
    :query QueryRoot
    :mutation MutationRoot}))

(defroutes app-routes
  (GET "/graphql" request (graphiql/handler request))
  (POST "graphql" request (graphql-handler request))
  (route/not-found "Page not found\n"))

(def app (handler/api app-routes))
