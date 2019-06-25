(ns workshop.structured-data)


(defrecord Course [code name prerequisites credits])
(defrecord Faculty [id first-name last-name])
(defrecord Offering [id course-name faculty-id days start end])

(defn add-course
  [catalog course]
  (assoc-in catalog [:courses] {(:code course) course}))