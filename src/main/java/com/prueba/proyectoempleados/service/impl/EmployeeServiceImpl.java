package com.prueba.proyectoempleados.service.impl;

import com.prueba.proyectoempleados.dao.EmployeeRepository;
import com.prueba.proyectoempleados.dto.EmployeeDTO;
import com.prueba.proyectoempleados.dto.EmployeePostDTO;
import com.prueba.proyectoempleados.model.Employee;
import com.prueba.proyectoempleados.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    employee.getIdEmployee(),
                    employee.getFirstName(),
                    employee.getLastName()
            );
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    @Override
    public EmployeeDTO addEmployee(EmployeePostDTO employeePostDTO) {
        Employee employee = new Employee();

        employee.setBirthDate(employeePostDTO.getBirthDate());
        employee.setFirstName(employeePostDTO.getFirstName());
        employee.setLastName(employeePostDTO.getLastName());
        employee.setGender(employeePostDTO.getGender());
        employee.setHireDate(employeePostDTO.getHireDate());

        employee =employeeRepository.save(employee);

        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getIdEmployee(),
                employee.getFirstName(),
                employee.getLastName()
        );

        return employeeDTO;
    }
}
