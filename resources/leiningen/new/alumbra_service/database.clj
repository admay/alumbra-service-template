(ns {{name}}.database
    (:require
     [{{name}}.specs :refer :all]
     [clojure.spec.alpha :as s]
     [clojure.java.jdbc :as jdbc]))

(defn row->person
  [{:keys [id first_name last_name]}]
  {:id id
   :first-name first_name
   :last-name last_name})

(defn row-to-property
  [{:keys [id owner_id address size]}]
  {:id id
   :owner-id owner_id
   :address address
   :size size})

(defn fetch-person-by-id
  [db id]
  (if-let [person (first (jdbc/query db ["select * from people where id = ?" id]))]
    person
    nil))

(defn fetch-property-by-id
  [db id]
  (if-let [property (jdbc/query db ["select * from properties where id = ?" id])]
    (row-to-property property)
    nil))

(defn fetch-properties-by-owner
  [db owner-id]
  (if-let [properties (seq (jdbc/query db ["select * from properties where owner_id = ?" owner-id]))]
    (map row-to-property properties)
    nil))

(defn create-person!
  [db data]
  (if-let [person (seq (jdbc/insert! db :people data))]
    (row->person (first person))
    nil))

(defn create-property!
  [db data]
  (if-let [property (seq (jdbc/insert! db :properties data))]
    (row-to-property (first property))
    nil))
