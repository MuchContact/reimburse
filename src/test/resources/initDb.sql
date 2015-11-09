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

CREATE TABLE expense_report_items (
     id INT NOT NULL AUTO_INCREMENT,
     reportId INT NOT NULL,
     expenseId INT NOT NULL,
     PRIMARY KEY (id)
);


CREATE TABLE audit_expense_report (
     id INT NOT NULL AUTO_INCREMENT,
     referenceExpenseReport VARCHAR(120),
	   referencedPolicy VARCHAR(120),
	   approvedAmount DOUBLE,
     PRIMARY KEY (id)
);


CREATE TABLE audit_expense (
     id INT NOT NULL AUTO_INCREMENT,
     referencedExpense VARCHAR(120),
	 referencedPolicy VARCHAR(120),
	 approvedAmount DOUBLE,
     PRIMARY KEY (id)
);


CREATE TABLE audit_report_items (
     id INT NOT NULL AUTO_INCREMENT,
     auditReportId INT NOT NULL,
     auditExpenseId INT NOT NULL,
     PRIMARY KEY (id)
);