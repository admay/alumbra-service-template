(ns {{name}}.database
    (:require
     [{{name}}.specs :refer :all]
     [clojure.spec.alpha :as s]
     [clojure.java.jdbc :as jdbc]))

(defn row-to-person
  [{:keys [id first_name last_name]}]
  {:pre [(and (s/valid? :owner/id )
              (s/valid? :owner/first-name first_name)
              (s/valid? :owner/last-name last_name))]
   :post [(s/valid? ::person %)]}
  {:id id
   :first-name first_name
   :last-name last_name})

(defn row-to-property
  [{:keys [id owner_id address size]}]
  {:pre [(and (s/valid? :property/id id)
              (s/valid? :property/size size)
              (s/valid? :property/address address)
              (s/valid? :owner/id owner_id))]
   :post [(s/valid? ::property %)]}
  {:id id
   :owner-id owner_id
   :address address
   :size size})

(defn fetch-person-by-id
  [db id]
  {:pre [(s/valid? :owner/id id)]
   :post [(or (s/valid? ::person %) nil)]}
  (if-let [person (first (jdbc/query db ["select * from people where id = ?" id]))]
    (row-to-person person)
    nil))

(defn fetch-property-by-id
  [db id]
  {:pre [(s/valid? :property/id id)]
   :post [(or (s/valid? ::property %) nil)]}
  (if-let [property (jdbc/query db ["select * from properties where id = ?" id])]
    (row-to-property property)
    nil))

(defn fetch-properties-by-owner
  [db owner-id]
  {:pre [(s/valid? :owner/id)]
   :post [(or nil (s/valid? (s/coll-of ::property) %))]}
  (if-let [properties (seq (jdbc/query db ["select * from properties where owner_id = ?" owner-id]))]
    (map row-to-property properties)
    nil))

(defn create-person!
  [db data]
  {:pre [(s/valid? ::person data)]
   :post [(or (s/valid? ::person %) nil)]}
  (if-let [person (seq (jdbc/insert! db :people data))]
    (row-to-person person)
    nil))

(defn create-property!
  [db data]
  {:pre [(s/valid? ::property data)]
   :post [(or (s/valid? ::property %) nil)]}
  (if-let [property (seq (jdbc/insert! db ::properties data))]
    (row-to-property property)
    nil))
