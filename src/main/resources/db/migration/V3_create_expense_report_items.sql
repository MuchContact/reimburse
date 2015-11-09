CREATE TABLE expense_report_items (
     id INT NOT NULL AUTO_INCREMENT,
     reportId INT NOT NULL,
     expenseId INT NOT NULL,
     PRIMARY KEY (id)
);