package org.qasimovey.service;

import org.qasimovey.model.ClientCritetia;
import org.qasimovey.model.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
     List<EmployeeDto> getEmployees(ClientCritetia criteria);

     void callForInterview(List<Long> emps_ids);
}
