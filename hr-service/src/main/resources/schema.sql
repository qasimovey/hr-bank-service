DROP TABLE IF EXISTS EMPLOYEES_INFO;

CREATE TABLE EMPLOYEES_INFO (
                                id INT AUTO_INCREMENT  PRIMARY KEY,
                                firstName VARCHAR(250) NOT NULL,
                                lastName VARCHAR(250) NOT NULL,
                                birthDate DATE,
                                experience number,
                                profession number,
                                gender number,
                                STATE number
);