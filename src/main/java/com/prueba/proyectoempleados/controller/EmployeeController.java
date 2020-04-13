package com.prueba.proyectoempleados.controller;

import com.prueba.proyectoempleados.dto.EmployeeDTO;
import com.prueba.proyectoempleados.dto.EmployeePostDTO;
import com.prueba.proyectoempleados.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> getAll() {
        return  employeeService.findAll();

    }

    @PostMapping
    public EmployeeDTO addEmployee(@RequestBody EmployeePostDTO employeePostDTO) {
            return employeeService.addEmployee(employeePostDTO);

    }

}
