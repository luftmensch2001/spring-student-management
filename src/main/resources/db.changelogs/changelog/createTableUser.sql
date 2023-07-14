CREATE TABLE `student_management`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(20) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `role` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`user_id`));
