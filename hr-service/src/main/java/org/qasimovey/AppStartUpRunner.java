package org.qasimovey;


import lombok.RequiredArgsConstructor;
import org.qasimovey.dao.EmployeeDAO;
import org.qasimovey.model.entity.Employee;
import org.qasimovey.utils.enums.Gender;
import org.qasimovey.utils.enums.Profession;
import org.qasimovey.utils.enums.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AppStartUpRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppStartUpRunner.class);
    private final EmployeeDAO employeeDAO;

    @Override
    public void run(String... args) throws Exception {
/*
        employeeDAO.cleanDB();
        employeeDAO.saveAll(prepareData());
        logger.info("Melumatlar bazaya daxil edildi");
*/

    }

    private List<Employee> prepareData() {
        Random r = new Random(0);
        List<Employee> employeeList = new ArrayList<>();
        long id = 1L;
        //add poeple in working mode
        for (; id < 10; id++) {
            int exp = Math.abs(r.nextInt() % 15);
            Employee e = new Employee(id, UUID.randomUUID().toString().substring(1, 10), "NOT_WORK", LocalDate.of(1980, 12, 12), exp, Profession.PROGRAMMER, Gender.MALE, State.NOT_WORKING);
            employeeList.add(e);
        }

        //add people in non-working mode
        for (; id < 15; id++) {
            int exp = Math.abs(r.nextInt() % 15);

            Employee e = new Employee(id, UUID.randomUUID().toString().substring(1, 10), "WORK", LocalDate.of(1990, 6, 6), exp, Profession.PROGRAMMER, Gender.MALE, State.WORKING);
            employeeList.add(e);

        }

        //add people in waiting mode
        for (; id < 18; id++) {
            int exp = Math.abs(r.nextInt() % 15);
            Employee e = new Employee(id, UUID.randomUUID().toString().substring(1, 10), "WAIT", LocalDate.of(2000, 3, 3), exp, Profession.PROGRAMMER, Gender.MALE, State.WAITING_FOR_INTERVIEW);
            employeeList.add(e);

        }
        return employeeList;
    }
}
