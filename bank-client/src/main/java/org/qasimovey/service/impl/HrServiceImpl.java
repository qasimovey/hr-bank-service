package org.qasimovey.service.impl;

import org.qasimovey.client.HrServiceClient;
import org.qasimovey.model.dto.EmployeeCriteriaDto;
import org.qasimovey.model.entity.Employee;
import org.qasimovey.service.HrService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrServiceImpl implements HrService {

    private final HrServiceClient hrServiceClient;

    public HrServiceImpl(final HrServiceClient hrServiceClient){
        this.hrServiceClient=hrServiceClient;
    }

    public void callForInterview(List<Long> ids){
        hrServiceClient.callForInterview(ids);
    }
    public List<Employee> getEmployeesBasedOnCriteria(EmployeeCriteriaDto criteriaDto){
       return hrServiceClient.getEmployeesBasedOnCriteria(criteriaDto);
    }


}
