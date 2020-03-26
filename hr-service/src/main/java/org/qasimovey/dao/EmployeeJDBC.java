package org.qasimovey.dao;

import org.qasimovey.model.ValidCriteria;
import org.qasimovey.model.entity.Employee;
import org.qasimovey.utils.ColumnUtils;
import org.qasimovey.utils.EmployeeRowMapper;
import org.qasimovey.utils.TableUtils;
import org.qasimovey.utils.enums.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class EmployeeJDBC implements EmployeeDAO {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeJDBC.class);
    private final JdbcTemplate jdbcTemplate;

    // private final String projection="SELECT * FROM " + TableUtils.EMPLOYEE;
    // private final String selection="WHERE "+ ColumnUtils.EXP+"<=?"+" AND "+ColumnUtils.PROFESSION+"=?";
    public EmployeeJDBC(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Employee> fetchEmployees(ValidCriteria validCriteria) {
        final String projection = "SELECT * FROM " + TableUtils.EMPLOYEE;
        final String selection = " WHERE " + ColumnUtils.EXP + ">=" + validCriteria.getExperience() + " AND " + ColumnUtils.PROFESSION + "=" + validCriteria.getProfession_code();

        List<Employee> employeeList = jdbcTemplate.query(projection + selection, new EmployeeRowMapper());
        return employeeList;
    }

    public void updateStateOfEmployees(List<Long> emps_ids) {
        logger.info("BATCH UPDATE STARTED : " + emps_ids.size());
        batchUpdate(emps_ids);
        logger.info("BATCH UPDATE FINISHED : " + emps_ids.size());
    }

    private int[] batchUpdate(final List<Long> emps_ids) {
        int[] updateCounts = jdbcTemplate.batchUpdate(
                "update " + TableUtils.EMPLOYEE + " SET " + ColumnUtils.STATE + "= ? where " + ColumnUtils.ID + " = ?",
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, State.WAITING_FOR_INTERVIEW.code);
                        ps.setLong(2, emps_ids.get(i));
                    }

                    public int getBatchSize() {
                        return emps_ids.size();
                    }
                });
        return updateCounts;
    }

    public void saveAll(List<Employee> employeeList) {
        logger.info("MASS INSERT STARTED");
        batchInsert(employeeList);
        logger.info("MASS INSERT FINISHED");

    }

    private int[] batchInsert(final List<Employee> employeeList) {
        String q1 = "INSERT INTO " + TableUtils.EMPLOYEE;
        String q2 = "(" + ColumnUtils.ID + "," + ColumnUtils.FIRST_NAME + "," + ColumnUtils.LAST_NAME + "," + ColumnUtils.BIRTH_DATE + "," + ColumnUtils.STATE + "," + ColumnUtils.SEX + "," + ColumnUtils.PROFESSION + "," + ColumnUtils.EXP + ")";
        String q3 = " VALUES(?,?,?,?,?,?,?,?)";
        int[] updateCounts = jdbcTemplate.batchUpdate(
                q1 + q2 + q3,
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, employeeList.get(i).getId());
                        ps.setString(2, employeeList.get(i).getFirstName());
                        ps.setString(3, employeeList.get(i).getLastName());
                        ps.setDate(4, java.sql.Date.valueOf(employeeList.get(i).getBirthDate()));
                        ps.setInt(5, employeeList.get(i).getState().code);
                        ps.setInt(6, employeeList.get(i).getGender().code);
                        ps.setInt(7, employeeList.get(i).getProfession().code);
                        ps.setInt(8, employeeList.get(i).getExperience());

                    }

                    public int getBatchSize() {
                        return employeeList.size();
                    }
                });
        return updateCounts;
    }

    public void cleanDB(){
        jdbcTemplate.execute("DELETE FROM "+TableUtils.EMPLOYEE);
    }
}
