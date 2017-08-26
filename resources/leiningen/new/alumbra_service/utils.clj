(ns {{name}}.utils
    (:require
     [clojure.walk :as walk]
     [clojure.string :as string]))

(defn transform-keys
  "Recursively transforms all map keys in coll with t."
  [t coll]
  (let [f (fn [[k v]] [(t k) v])]
    (walk/postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) coll)))

(defn underscore->hyphen
  [s]
  (keyword (string/replace (string/replace s #"_" "-") #":" "")))

(defn hyphen->underscore
  [s]
  (string/replace (string/replace s #"-" "_") #":" ""))

(defn row->resolvable [row]
  (transform-keys underscore->hyphen row))



