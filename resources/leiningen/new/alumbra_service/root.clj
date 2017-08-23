(ns {{name}}.root
    (:require [{{name}}.resolvables :as r]))

(def QueryRoot
  {:person (r/->Person nil nil nil)
   :property (r/->Property nil nil nil nil)
   :properties (r/->Properties nil)})

(def MutationRoot
  {:create-person (r/->CreatePerson nil)
   :create-property (r/->CreateProperty nil)})

