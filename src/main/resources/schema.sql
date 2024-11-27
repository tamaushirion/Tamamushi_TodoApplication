CREATE TABLE todo_details (
  id INT AUTO_INCREMENT,
  title  VARCHAR(40),
  is_done BOOLEAN,
  time_limit DATE,
  PRIMARY KEY (id),
  UNIQUE KEY (title)
);