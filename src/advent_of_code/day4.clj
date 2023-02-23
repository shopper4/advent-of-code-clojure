(ns advent-of-code.day4
  (:require [clojure.string :as str]))

(defn- load-input
  []
  (str/split-lines (slurp "input_day4.txt")))

;; (defn- get-range
;;   [value-string]
;;   (let [vals (map #(Integer/parseInt %) (str/split value-string #"-"))
;;         [min-val max-val] vals]
;;     (apply range [min-val (+ 1 max-val)])))

(defn- get-min-max
  [value-string]
  (map #(Integer/parseInt %) (str/split value-string #"-")))

(defn- within-range?
  [[[min1 max1] [min2 max2]]]
  (or (and (>= min2 min1) (<= max2 max1)) 
      (and (>= min1 min2) (<= max1 max2))))

(defn part1
  "https://adventofcode.com/2022/day/3"
  [] 
  (let [values (map #(str/split % #",") (load-input))
        ranges (map #(map get-min-max %) values)
        x (filter within-range? ranges)]
    (count x)))


