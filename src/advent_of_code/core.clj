(ns advent-of-code.core
  (:require [clojure.string :as str]))

(defn day1-1
  "https://adventofcode.com/2022/day/1"
  []
  (let [elves (map (fn [x] (map #(Integer/parseInt %) x)) (remove #(= '("") %) (partition-by #(= "" %) (str/split-lines (slurp "input_day1.txt")))))]
    (first (sort > (map #(reduce + %) elves)))))

(defn day1-2
  "https://adventofcode.com/2022/day/1"
  []
  (let [elves (map (fn [x] (map #(Integer/parseInt %) x)) (remove #(= '("") %) (partition-by #(= "" %) (str/split-lines (slurp "input_day1.txt")))))
        top-elves (take 3 (sort > (map #(reduce + %) elves)))]
    (reduce + top-elves)))

(defn- match-result
  [player opponent]
  (case [player opponent]
    ["Rock" "Rock"] 3
    ["Rock" "Paper"] 0
    ["Rock" "Scissors"] 6
    ["Paper" "Rock"] 6
    ["Paper" "Paper"] 3
    ["Paper" "Scissors"] 0
    ["Scissors" "Rock"] 0
    ["Scissors" "Paper"] 6
    ["Scissors" "Scissors"] 3))

(def player-choices {"X" {:name "Rock" :score 1}
                     "Y" {:name "Paper" :score 2}
                     "Z" {:name "Scissors" :score 3}})

(def opponent-choices {"A" "Rock"
                       "B" "Paper"
                       "C" "Scissors"})

(defn- calculate-score
  [opponent-choice player-choice]
  (let [{player-choice-name :name
         player-choice-score :score} (get player-choices player-choice)
         opponent-choice-name (get opponent-choices opponent-choice)]
    (+ player-choice-score (match-result player-choice-name opponent-choice-name))))

(defn day2-1
  "https://adventofcode.com/2022/day/2"
  []
  (let [input (map #(str/split % #" ") (str/split-lines (slurp "input_day2.txt")))
        scores (map (fn [[opponent player]]
                      (calculate-score opponent player)) input)]
    (reduce + scores)))