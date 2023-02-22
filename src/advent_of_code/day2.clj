(ns advent-of-code.day2
  (:require [clojure.string :as str]))

(defn- read-input
  []
  (map #(str/split % #" ") (str/split-lines (slurp "input_day2.txt"))))

(defn- match-result
  [player opponent]
  (let [win 6
        draw 3
        lose 0] 
    (case [player opponent]
      ["Rock" "Rock"] draw
      ["Rock" "Paper"] lose
      ["Rock" "Scissors"] win
      ["Paper" "Rock"] win
      ["Paper" "Paper"] draw
      ["Paper" "Scissors"] lose
      ["Scissors" "Rock"] lose
      ["Scissors" "Paper"] win
      ["Scissors" "Scissors"] draw)))

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

(defn part1
  "https://adventofcode.com/2022/day/2"
  []
  (let [input (read-input)
        scores (map (fn [[opponent player]]
                      (calculate-score opponent player)) input)]
    (reduce + scores)))

(defn get-player-choice
  [opponent-choice outcome]
  (let [opponent-choice-name (get opponent-choices opponent-choice)]
    (case [opponent-choice-name outcome]
      ["Rock" "X"] "Z" ;; Lose, choose Scissors
      ["Rock" "Y"] "X" ;; Draw, choose Rock
      ["Rock" "Z"] "Y" ;; Win, choose Paper
      ["Paper" "X"] "X" ;; Lose, choose Rock
      ["Paper" "Y"] "Y" ;; Draw, choose Paper
      ["Paper" "Z"] "Z" ;; Win, choose Scissors
      ["Scissors" "X"] "Y" ;; Lose, choose Paper
      ["Scissors" "Y"] "Z" ;; Draw, choose Scissors
      ["Scissors" "Z"] "X"))) ;; Win, choose Rock

(defn- calculate-score-2
  [opponent-choice outcome]
  (let [player-choice (get-player-choice opponent-choice outcome)]
    (calculate-score opponent-choice player-choice)))

(defn part2
  "https://adventofcode.com/2022/day/2"
  []
  (let [input (read-input)
        scores (map (fn [[opponent outcome]]
                      (calculate-score-2 opponent outcome)) input)]
    (reduce + scores)))