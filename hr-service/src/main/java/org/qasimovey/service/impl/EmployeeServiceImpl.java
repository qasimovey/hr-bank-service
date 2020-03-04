package org.qasimovey.service.impl;

import org.qasimovey.dao.EmployeeDAO;
import org.qasimovey.exceptions.BadRequestException;
import org.qasimovey.exceptions.NotFoundException;
import org.qasimovey.model.ClientCritetia;
import org.qasimovey.model.ValidCriteria;
import org.qasimovey.model.dto.EmployeeDto;
import org.qasimovey.model.entity.Employee;
import org.qasimovey.service.EmployeeService;
import org.qasimovey.utils.Mapper;
import org.qasimovey.utils.enums.Profession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(final EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public List<EmployeeDto> getEmployees(ClientCritetia criteria) {
        //check criteria is valid or not
        ValidCriteria validCriteria=checkClientRequestValidity(criteria);

        List<Employee> employeeList = employeeDAO.fetchEmployees(validCriteria );
        if(employeeList.isEmpty())throw new NotFoundException("No Employee with provided criteria is found");
        return Mapper.mapFromEntitiestoDtos(employeeList);

    }

    private ValidCriteria checkClientRequestValidity(ClientCritetia criteria){
        logger.info("Validity of Client Request is checking");
        //this bundle contains valid and useful data to dao )
        //ie:proffession code is more usefull rather than its name in this case
        //because in db , we stored proffesion as integer representing their code
        ValidCriteria bundle=new ValidCriteria();
        //check criteria is valid or not
        String client_wanted = criteria.getProfession();
        int prof_code = -1;
        List<String> prof_list = new ArrayList<>();
        for (Profession p : Profession.values()) {
            prof_list.add(p.value);
            if (client_wanted.equalsIgnoreCase(p.value)) {
                prof_code = p.code;
            }
        }
        if (prof_code == -1) {
            String msg = "Proffesion " + criteria.getProfession() + " incorrect";
            String help = " \n Choose one of them :" + prof_list;
            throw new BadRequestException(msg + help);
        }
        bundle.setExperience(criteria.getExperience());
        bundle.setProfession_code(prof_code);
        logger.info("Client Request is VALID");
        return bundle;
    }



    public void callForInterview(List<Long> emps_ids){
        logger.info("CALL FOR INTERVIEW SERVICE :  "+emps_ids);
        if(emps_ids!=null && !emps_ids.isEmpty()){
            employeeDAO.updateStateOfEmployees(emps_ids);
        }

    }

}
