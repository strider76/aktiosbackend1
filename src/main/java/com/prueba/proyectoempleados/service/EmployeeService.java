package com.prueba.proyectoempleados.service;

import com.prueba.proyectoempleados.dto.EmployeeDTO;
import com.prueba.proyectoempleados.dto.EmployeePostDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> findAll();
    EmployeeDTO addEmployee(EmployeePostDTO employeePostDTO);

}
