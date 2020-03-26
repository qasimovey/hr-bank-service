package org.qasimovey.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.qasimovey.utils.enums.Gender;
import org.qasimovey.utils.enums.Profession;
import org.qasimovey.utils.enums.State;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int experience;
    private Profession profession;
    private Gender gender;
    private State state;

}


