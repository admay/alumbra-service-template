(ns {{name}}.resolvables
    (:require
     [claro.data :as data]
     [{{name}}.database :as db]))

(defrecord Person
    data/Resolvable
  [id first-name last-name]
  (resolve! [_ env]
    {:id id
     :first-name first-name
     :last-name last-name}))

(defrecord Property
    data/Resolvable
  [id owner-id address size]
  (resolve! [_ env]
    {:id id
     :owner (->PersonById owner-id)
     :address address
     :size size}))

(defrecord PersonById
    data/Resolvable
  [id]
  (resolve! [_ env]
    (map->Person (db/fetch-person-by-id (:database-url env) id))))

(defrecord PropertyById
    data/Resolvable
  [id]
  (resolve! [_ env]
    (map->Property (db/fetch-property-by-id (:database-url env) id))))

(defrecord PropertiesByOwner
    data/Resolvable
  [owner-id]
  (resolve! [_ env]
    (map map->Property (db/fetch-properties-by-owner (:database-url env) owner-id))))

(defrecord CreatePerson
    data/Resolveable
  [data]
  (resolve! [_ env]
    (map->Person (db/create-person! (:database-url env) data))))

(defrecord CreateProperty
    data/Resolveable
  [data]
  (resolve! [_ env]
    (map->Property (db/create-property! (:database-url env) data))))
