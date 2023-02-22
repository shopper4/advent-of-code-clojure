(ns advent-of-code.core
  (:require [clojure.string :as str]))

(defn day1-1
  "https://adventofcode.com/2022/day/1"
  []
  (let [elves (map (fn [x] (map #(Integer/parseInt %) x)) (remove #(= '("") %) (partition-by #(= "" %) (str/split-lines (slurp "input_day1.txt")))))]
    (first (sort > (map #(reduce + %) elves)))))