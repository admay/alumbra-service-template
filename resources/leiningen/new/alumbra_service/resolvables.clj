(ns {{name}}.resolvables
    (:require
     [claro.data :as data]
     [{{name}}.database :as db]))

(declare ->PersonById)

(defrecord Person
    [id first-name last-name]
    data/Resolvable
  (resolve! [_ env]
    {:id id
     :first-name first-name
     :last-name last-name}))

(defrecord Property
    [id owner-id address size]
    data/Resolvable
  (resolve! [_ env]
    {:id id
     :owner (->PersonById owner-id)
     :address address
     :size size}))

(defrecord PersonById
    [id]
    data/Resolvable
  (resolve! [_ env]
    (map->Person (db/fetch-person-by-id (:database-url env) id))))

(defrecord PropertyById
    [id]
    data/Resolvable
  (resolve! [_ env]
    (map->Property (db/fetch-property-by-id (:database-url env) id))))

(defrecord Properties
    [owner-id]
    data/Resolvable
  (resolve! [_ env]
    (map map->Property (db/fetch-properties-by-owner (:database-url env) owner-id))))

(defrecord CreatePerson
    [data]
    data/Resolvable
  (resolve! [_ env]
    (map->Person (db/create-person! (:database-url env) data))))

(defrecord CreateProperty
    [data]
    data/Resolvable
  (resolve! [_ env]
    (map->Property (db/create-property! (:database-url env) data))))
