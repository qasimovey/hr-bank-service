package org.qasimovey.utils;

import org.qasimovey.model.dto.EmployeeDto;
import org.qasimovey.model.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static EmployeeDto mapFromEntityToDto(Employee entity) {
        EmployeeDto dto = new EmployeeDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setExperience(entity.getExperience());
        dto.setBirthDate(entity.getBirthDate());
        dto.setProfession(entity.getProfession().value);
        dto.setId(entity.getId());
        dto.setGender(entity.getGender().value);
        return dto;
    }

    public static List<EmployeeDto> mapFromEntitiestoDtos(List<Employee> entities) {
        return entities.stream().map(entity -> mapFromEntityToDto(entity))
                                .collect(Collectors.toList());

    }

}
