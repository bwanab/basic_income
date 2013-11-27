(ns basic-income.core
  (:use [incanter core charts stats]))

(def num-adults 227e6)
(def basic-income (* 7.25 40 50))
(def labor-force 154e6)
(def disabled-adults 21e6)
(def wealth-transfers 3369e9)

(defn jk-rowling [non-workers]
  (let [num-of-jk-rowlings (sample-binomial 1 :size non-workers :prob 1e-7)]
    (* num-of-jk-rowlings 1e9)))

(defn basic-income-cost-benefit []
  (let [direct-costs (* num-adults basic-income)
        administrative-cost-per-person (sample-normal 1 :mean 250 :sd 75)
        non-worker-multiplier (first (sample-uniform 1 :min -0.05 :max 0.15))
        non-workers (* (- num-adults labor-force disabled-adults)
                       (+ 1 non-worker-multiplier))
        marginal-worker-hourly-productivity (sample-normal 1 :mean 10 :sd 1)
        administrative-costs (* num-adults administrative-cost-per-person)
        labor-effect-costs-benefit (* -1 (* (- num-adults labor-force disabled-adults)
                                            non-worker-multiplier
                                            (* 40 52 marginal-worker-hourly-productivity)))]
    (- (+ direct-costs administrative-costs labor-effect-costs-benefit)
       (jk-rowling non-workers))))

(defn basic-job-cost-benefit []
  (let [administrative-cost-per-disabled-person (sample-normal 1 :mean 500 :sd 150)
        administrative-cost-per-worker (sample-normal 1 :mean 5000 :sd 1500)
        non-worker-multiplier (first (sample-uniform 1 :min -0.20 :max 0.25))
        basic-job-hourly-productivity (first (sample-uniform 1 :min 0 :max 7.25))
        disabled-cost (* disabled-adults (+ basic-income administrative-cost-per-disabled-person))
        num-basic-workers (* (- num-adults disabled-adults labor-force)
                             (+ 1 non-worker-multiplier))
        basic-worker-cost-benefit (* num-basic-workers
                                     (+ basic-income (- administrative-cost-per-worker
                                                        (* 40 50 basic-job-hourly-productivity))))]
    (+ disabled-cost basic-worker-cost-benefit)))

"Monty Carlo time!"
(def N (* 1024 12))

(def bi (map (fn [_] (basic-income-cost-benefit)) (range N)))
(def bj (map (fn [_] (basic-job-cost-benefit)) (range N)))

(view (histogram bi :nbins 50 :legend true :series-label "basic-income-cost-benefit"))
(view (histogram bj :nbins 50 :legend true :series-label "basic-job-cost-benefit"))
