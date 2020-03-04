package org.qasimovey.client;

import org.qasimovey.model.dto.EmployeeCriteriaDto;
import org.qasimovey.model.entity.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(value = "hrServiceClient",url = "http://localhost:9090/"/*,fallback = HrServiceFallback.class*/)
public interface HrServiceClient {

    @PostMapping("/api/employees")
    List<Employee> getEmployeesBasedOnCriteria(EmployeeCriteriaDto criteriaDto);

    @PutMapping("/api/employees")
    void callForInterview(List<Long> employeesIds);
}
