package org.qasimovey.controller;

import org.qasimovey.exceptions.ServiceException;
import org.qasimovey.model.ClientCritetia;
import org.qasimovey.model.dto.EmployeeDto;
import org.qasimovey.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //get employee based on requirements
    @PostMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@RequestBody ClientCritetia critetia) {
        logger.info("GET in Controller triggered");
        return ResponseEntity.ok(employeeService.getEmployees(critetia));
    }

    //update state of employee
    @PutMapping
    public ResponseEntity<String> updateStateOfEmployees(@RequestBody List<Long> emps_ids) {
        logger.info(" clientin sorgusu+" + emps_ids);
        employeeService.callForInterview(emps_ids);
        return ResponseEntity.accepted().body("OK");
    }
}
