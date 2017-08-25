(ns {{name}}.resolvables
  (:require
   [claro.data :as data]
   [manifold.deferred :as d]
   [clojure.walk :as walk]
   [{{name}}.database :refer :all]))

;; Queries
(defrecord Person [id]
  data/Resolvable
  (resolve! [_ {:keys [db] :as env}]
    (d/future
      (fetch-person db id))))

(defrecord Property [id]
  data/Resolvable
  (resolve! [_ {:keys [db] :as env}]
    (d/future
      (fetch-property db id))))

(defrecord Properties [owner]
  data/Resolvable
  (resolve! [_ {:keys [db] :as env}]
    (map map->Property (fetch-properties db owner))))

;; Mutations
(defrecord CreatePerson [data]
  data/Resolvable
  (resolve! [_ env]
    (map->Person (create-person! (:database-url env) data))))

(defrecord CreateProperty [data]
  data/Resolvable
  (resolve! [_ env]
    (map->Property (create-property! (:database-url env) data))))
