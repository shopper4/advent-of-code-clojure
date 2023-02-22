(ns advent-of-code.day1
  (:require [clojure.string :as str]))

(defn part1
  "https://adventofcode.com/2022/day/1"
  []
  (let [elves (map (fn [x] (map #(Integer/parseInt %) x)) (remove #(= '("") %) (partition-by #(= "" %) (str/split-lines (slurp "input_day1.txt")))))]
    (first (sort > (map #(reduce + %) elves)))))

(defn part2
  "https://adventofcode.com/2022/day/1"
  []
  (let [elves (map (fn [x] (map #(Integer/parseInt %) x)) (remove #(= '("") %) (partition-by #(= "" %) (str/split-lines (slurp "input_day1.txt")))))
        top-elves (take 3 (sort > (map #(reduce + %) elves)))]
    (reduce + top-elves)))