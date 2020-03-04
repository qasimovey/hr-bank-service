package org.qasimovey.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int experience;
    private String profession;
    private String gender;
}