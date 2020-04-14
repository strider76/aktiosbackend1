package com.prueba.proyectoempleados.service;

import com.prueba.proyectoempleados.dto.EmployeeDTO;
import com.prueba.proyectoempleados.dto.EmployeePostDTO;
import com.prueba.proyectoempleados.exception.EmployeeNotFoundException;
import com.prueba.proyectoempleados.model.Gender;

import java.util.Date;
import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> findAll();
    EmployeeDTO addEmployee(EmployeePostDTO employeePostDTO);

    void updateEmployee(Integer idEmployee, EmployeePostDTO employeePostDTO) throws EmployeeNotFoundException;

    void deleteEmployee(Integer idEmployee)  throws EmployeeNotFoundException;

    List<EmployeeDTO> getEmployeesByGender(Gender gender);

    List<EmployeeDTO> getEmployeesByBirthDate(Date startDate, Date endDate, Gender gender);

    EmployeeDTO getEmployeeBycompleteName(String firstName, String lastName) throws EmployeeNotFoundException;
}
