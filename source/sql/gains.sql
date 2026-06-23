CREATE TABLE gains (
    gain_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    record_date DATE NOT NULL,
    gain_sum INT NOT NULL DEFAULT 0,
    ap_count INT NOT NULL DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO gains (
    user_id,
    record_date,
    gain_sum,
    ap_count
)
VALUES
-- user 1
(1, '2026-06-01', 120, 1),
(1, '2026-06-02', 260, 2),
(1, '2026-06-03', 180, 0),

-- user 2
(2, '2026-06-01', 90, 0),
(2, '2026-06-02', 210, 1),
(2, '2026-06-03', 150, 0),

-- user 3
(3, '2026-06-01', 140, 3),
(3, '2026-06-02', 320, 1),
(3, '2026-06-03', 110, 0),

-- user 4
(4, '2026-06-01', 80, 0),
(4, '2026-06-02', 190, 2),
(4, '2026-06-03', 240, 0),

-- user 5
(5, '2026-06-01', 160, 3),
(5, '2026-06-02', 300, 1),
(5, '2026-06-03', 130, 1),

-- user 6
(6, '2026-06-01', 200, 2),
(6, '2026-06-02', 340, 1),
(6, '2026-06-03', 170, 0);