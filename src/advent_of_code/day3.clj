(ns advent-of-code.day3
  (:require [clojure.set :as set]
            [clojure.string :as str]))

(defn- load-input
  [] 
  (str/split-lines (slurp "input_day3.txt")))

(defn- is-upper-case? 
  [c] 
  (and (> (int c) 65) (< (int c) 91)))

(defn- priority 
  [c]
  (if (is-upper-case? c)
    (- (int c) 38)
    (- (int c) 96)))

(defn- find-error-items
  [rucksack]
  (set/intersection
   (set (subs rucksack 0 (/ (.length rucksack) 2)))
   (set (subs rucksack (/ (.length rucksack) 2)))))

(defn part1 
  "https://adventofcode.com/2022/day/3"
  []
  (reduce + (flatten (map (fn [x] (map priority (find-error-items x))) (load-input)))))