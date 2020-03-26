package org.qasimovey.dao;

import org.qasimovey.model.ValidCriteria;
import org.qasimovey.model.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> fetchEmployees(ValidCriteria validCriteria);

    void updateStateOfEmployees(List<Long> emps_ids);

    void saveAll(List<Employee> employeeList);

    void cleanDB();

}
