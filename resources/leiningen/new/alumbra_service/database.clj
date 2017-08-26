(ns {{name}}.database
    (:require
     [{{name}}.specs :refer :all]
     [{{name}}.utils :as utils]
     [clojure.spec.alpha :as s]
     [clojure.java.jdbc :as jdbc]))

(defn- fetch-from-db [db table id result-set-fn]
    (jdbc/get-by-id db table id {:result-set-fn result-set-fn}))

(defn fetch-person [db id]
  (if-let [person (fetch-from-db db :people id utils/row->resolvable)]
    person
    nil))

(defn fetch-property
  [db id]
  (if-let [property (fetch-from-db db :properties id utils/row->resolvable)]
    (assoc property :owner (fetch-person db (:owner-id property)))
    nil))

(defn fetch-properties
  [db owner-id]
  (if-let [properties (seq (jdbc/query db ["select * from properties where owner_id = ?" owner-id] {:result-set-fn utils/row->resolvable}))]
    (map #(assoc % :owner (fetch-person db (:owner-id %))) properties)
    nil))

(defn create-person!
  [db data]
  (if-let [person (first (seq (jdbc/insert! db :people data)))]
    person
    nil))

(defn create-property!
  [db data]
  (if-let [property (first (seq (jdbc/insert! db :properties data)))]
    property)
  nil)
