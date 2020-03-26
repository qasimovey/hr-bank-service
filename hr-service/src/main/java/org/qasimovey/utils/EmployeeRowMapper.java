package org.qasimovey.utils;

import org.qasimovey.model.entity.Employee;
import org.qasimovey.utils.enums.Gender;
import org.qasimovey.utils.enums.Profession;
import org.qasimovey.utils.enums.State;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {

        Employee e = new Employee();
        e.setId(resultSet.getLong(ColumnUtils.ID));
        e.setFirstName(resultSet.getString(ColumnUtils.FIRST_NAME));
        e.setLastName(resultSet.getString(ColumnUtils.LAST_NAME));
        e.setBirthDate(resultSet.getDate(ColumnUtils.BIRTH_DATE).toLocalDate());
        e.setExperience(resultSet.getInt(ColumnUtils.EXP));
        e.setState(determineState(resultSet.getInt(ColumnUtils.STATE)));
        e.setGender(determineGender(resultSet.getInt(ColumnUtils.SEX)));
        e.setProfession(determineProfession(resultSet.getInt(ColumnUtils.PROFESSION)));

        return e;
    }

    private State determineState(int s) {
        for (State state : State.values()) {
            if (state.code == s) {
                return state;
            }
        }
        return null;
    }

    private Profession determineProfession(int prof_code) {
        for (Profession p : Profession.values()) {
            if (prof_code == p.code) {
                return p;
            }
        }
        return null;
    }

    private Gender determineGender(final int code) {
        return code == Gender.MALE.code ? Gender.MALE : Gender.FEMALE;
    }
}
