CREATE TABLE audit_expense (
     id INT NOT NULL AUTO_INCREMENT,
     referencedExpense VARCHAR(120),
	 referencedPolicy VARCHAR(120),
	 approvedAmount DOUBLE,
     PRIMARY KEY (id)
);