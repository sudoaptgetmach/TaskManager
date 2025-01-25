CREATE TABLE tasks (
   id INT PRIMARY KEY AUTO_INCREMENT,
   title VARCHAR(255) NOT NULL,
   description TEXT NULL,
   due_date DATE NOT NULL,
   priority ENUM('LOW', 'MEDIUM', 'HIGH') NOT NULL,
   status ENUM('PENDING', 'IN_PROGRESS', 'COMPLETED') NOT NULL,
   category_id INT,
   user_id INT,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   FOREIGN KEY (category_id) REFERENCES categories(id),
   FOREIGN KEY (user_id) REFERENCES users(id)
);