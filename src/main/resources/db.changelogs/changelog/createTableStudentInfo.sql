CREATE TABLE `student_management`.`student_info` (
  `info_id` INT NOT NULL AUTO_INCREMENT,
  `student_id` INT NOT NULL,
  `address` VARCHAR(255) NULL,
  `average_score` DOUBLE NULL,
  `date_of_birth` DATETIME NULL,
  PRIMARY KEY (`info_id`, `student_id`));