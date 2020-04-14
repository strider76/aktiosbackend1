package com.prueba.proyectoempleados.controller;

import com.prueba.proyectoempleados.dto.EmployeeDTO;
import com.prueba.proyectoempleados.dto.EmployeePostDTO;
import com.prueba.proyectoempleados.exception.EmployeeNotFoundException;
import com.prueba.proyectoempleados.model.Gender;
import com.prueba.proyectoempleados.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    //GET  read
    //POST insert
    //PUT update
    //DELETE delete

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO> getAll() {
        return  employeeService.findAll();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO addEmployee(@RequestBody EmployeePostDTO employeePostDTO) {
            return employeeService.addEmployee(employeePostDTO);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@PathVariable("id") Integer idEmployee, @RequestBody EmployeePostDTO employeePostDTO) throws EmployeeNotFoundException {
        employeeService.updateEmployee(idEmployee, employeePostDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable("id") Integer idEmployee) throws EmployeeNotFoundException {
        employeeService.deleteEmployee(idEmployee);
    }

    @GetMapping("/gender/{gender}")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO> getEmployeeByGender(@PathVariable("gender") Gender gender) {
        return employeeService.getEmployeesByGender(gender);
    }

    @GetMapping("/searchbirthdate")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO> getEmployeesByDateAndGender(
            @RequestParam(name = "startdate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date startDate,
            @RequestParam(name = "enddate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date endDate,
            @RequestParam(name = "gender", required = false, defaultValue = "M") Gender gender) {
                return employeeService.getEmployeesByBirthDate(startDate,endDate,gender);
    }

    @GetMapping("/searcname")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO getEmployeeByCompleteName (
            @RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName) throws EmployeeNotFoundException {
        return employeeService.getEmployeeBycompleteName(firstName,lastName);
    }
}
