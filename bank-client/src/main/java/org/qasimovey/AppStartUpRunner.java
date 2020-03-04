package org.qasimovey;

import org.qasimovey.exception.BadRequestException;
import org.qasimovey.model.dto.EmployeeCriteriaDto;
import org.qasimovey.model.entity.Employee;
import org.qasimovey.service.HrService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppStartUpRunner implements CommandLineRunner {
    private final HrService service;

    public AppStartUpRunner(final HrService service) {
        this.service = service;

    }

    @Override
    public void run(String... args) {
        EmployeeCriteriaDto criteriaDto = new EmployeeCriteriaDto("PROGRAM8MER", 5);
        List<Employee> employeeList = null;
        try {
            employeeList = service.getEmployeesBasedOnCriteria(criteriaDto);
            System.out.println(employeeList);

        } catch (Exception e) {
            System.out.println("------------------");
            System.out.println("2." + e.getCause());
        }
    }
}
