CREATE TABLE audit_expense_report (
     id INT NOT NULL AUTO_INCREMENT,
     referenceExpenseReport VARCHAR(120),
	   referencedPolicy VARCHAR(120),
	   approvedAmount DOUBLE,
     PRIMARY KEY (id)
);