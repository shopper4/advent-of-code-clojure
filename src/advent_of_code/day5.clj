(ns advent-of-code.day5
  (:require [clojure.string :as str]))

;; [N] [G]                     [Q]
;; [H] [B]         [B] [R]     [H]
;; [S] [N]     [Q] [M] [T]     [Z]
;; [J] [T]     [R] [V] [H]     [R] [S]
;; [F] [Q]     [W] [T] [V] [J] [V] [M]
;; [W] [P] [V] [S] [F] [B] [Q] [J] [H]
;; [T] [R] [Q] [B] [D] [D] [B] [N] [N]
;; [D] [H] [L] [N] [N] [M] [D] [D] [B]
;; 1   2   3   4   5   6   7   8   9

(def stacks ['("N" "H" "S" "J" "F" "W" "T" "D")
             '("G" "B" "N" "T" "Q" "P" "R" "H")
             '("V" "Q" "L")
             '("Q" "R" "W" "S" "B" "N")
             '("B" "M" "V" "T" "F" "D" "N")
             '("R" "T" "H" "V" "B" "D" "M")
             '("J" "Q" "B" "D")
             '("Q" "H" "Z" "R" "V" "J" "N" "D")
             '("S" "M" "H" "N" "B")])

(defn- load-input
  []
  (str/split-lines (slurp "input_day5.txt")))

(defn- parse-move
  [move-str]
  (let [[quantity from to] (map #(Integer/parseInt %) (re-seq #"\d+" move-str))
        from (- from 1)
        to (- to 1)]
    [quantity from to]))

(defn- move
  [from to quantity]
  (let [items (take quantity from)]
    (apply conj to items)))

(defn- move-part-2
  [from to quantity]
  (let [items (reverse (take quantity from))]
    (apply conj to items)))

(defn part1
  "https://adventofcode.com/2022/day/5"
  []
  (let [moves (map #(parse-move %) (load-input))]
    (reduce (fn [acc [quantity from-index to-index]]
              (let [from-stack (nth acc from-index)
                    to-stack (nth acc to-index)
                    to-stack (move from-stack to-stack quantity)
                    from-stack (drop quantity from-stack)
                    acc (assoc acc to-index to-stack)
                    acc (assoc acc from-index from-stack)]
                acc)) stacks moves)))


(defn part2
  "https://adventofcode.com/2022/day/5#part2"
  []
  (let [moves (map #(parse-move %) (load-input))]
    (reduce (fn [acc [quantity from-index to-index]]
              (let [from-stack (nth acc from-index)
                    to-stack (nth acc to-index)
                    to-stack (move-part-2 from-stack to-stack quantity)
                    from-stack (drop quantity from-stack)
                    acc (assoc acc to-index to-stack)
                    acc (assoc acc from-index from-stack)]
                acc)) stacks moves)))