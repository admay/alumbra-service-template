(ns {{name}}.server
    (:require
     [clojure.java.io :as io]
     [compojure.core :refer [GET POST defroutes]]
     [compojure.route :as route]
     [compojure.handler :as handler]
     [alumbra.core :as alumbra]
     [alumbra.web.graphiql-workspace :as graphiql]
     [environ.core :refer [env]]
     [{{name}}.root :refer [QueryRoot MutationRoot]]))

(defn context []
  (fn [request]
    {:db (env :database-url)}))

(def graphql-handler
  (alumbra/handler
   {:schema (io/resource "schema.graphql")
    :query QueryRoot
    :mutation MutationRoot
    :context-fn (context)}))

(defroutes app-routes
  (GET "/" request (graphiql/handler request))
  (POST "/graphql" request (graphql-handler request))
  (route/not-found "Page not found\n"))

(def app (handler/api app-routes))
