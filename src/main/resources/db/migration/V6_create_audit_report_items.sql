CREATE TABLE audit_report_items (
     id INT NOT NULL AUTO_INCREMENT,
     auditReportId INT NOT NULL,
     auditExpenseId INT NOT NULL,
     PRIMARY KEY (id)
);