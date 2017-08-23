(ns {{name}}.specs
    (:require
     [clojure.spec.alpha :as s]))

;; Primitive Specs
(s/def :owner/first-name string?)
(s/def :owner/last-name string?)
(s/def :owner/id int?)
(s/def :property/id int?)
(s/def :property/address string?)
(s/def :property/size number?)

;; Object Specs
(s/def ::person
  (s/keys :req-un [:owner/id
                   :owner/first-name
                   :owner/last-name]))

(s/def ::property
  (s/keys :req-un [:property/id
                   :property/size
                   :property/address
                   :owner/id]))

;; Input Specs
;; Don't include ID's in the input specs
;; if they're going to be generated by the DB
(s/def ::person-input
  (s/keys :req-un [:owner/first-name
                   :owner/last-name]))

(s/def ::property-input
  (s/keys :req-un [:property/size
                   :property/address
                   :owner/id]))
