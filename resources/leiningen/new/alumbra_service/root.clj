(ns {{name}}.root
    (:require {{name}}.resolvables :as r))

(def QueryRoot
  {:person (r/->Person)
   :property (r/->Property)
   :properties (r/->Properties)})

(def MutationRoot
  {:person (r/->CreatePerson)
   :property (r/->CreateProperty)})

