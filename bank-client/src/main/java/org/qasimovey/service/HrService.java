package org.qasimovey.service;

import org.qasimovey.model.dto.EmployeeCriteriaDto;
import org.qasimovey.model.entity.Employee;

import java.util.List;

public interface HrService {
    List<Employee> getEmployeesBasedOnCriteria(EmployeeCriteriaDto criteriaDto);
    void callForInterview(List<Long> ids);
}
