CREATE TABLE expense (
     id INT NOT NULL AUTO_INCREMENT,
     name CHAR(30) NOT NULL,
     price DOUBLE ,
     PRIMARY KEY (id)
);

CREATE TABLE expense_report (
     id INT NOT NULL AUTO_INCREMENT,
     title CHAR(50) NOT NULL,
     PRIMARY KEY (id)
);
