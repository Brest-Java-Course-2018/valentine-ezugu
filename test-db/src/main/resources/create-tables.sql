DROP TABLE IF EXISTS department;
CREATE TABLE department (
  id INT NOT NULL AUTO_INCREMENT,
  departmentName VARCHAR(255) NOT NULL ,
  description VARCHAR(255)NULL ,
  PRIMARY KEY (id)
)