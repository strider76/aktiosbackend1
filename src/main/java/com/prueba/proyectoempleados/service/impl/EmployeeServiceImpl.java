package com.prueba.proyectoempleados.service.impl;

import com.prueba.proyectoempleados.dao.EmployeeRepository;
import com.prueba.proyectoempleados.dto.EmployeeDTO;
import com.prueba.proyectoempleados.dto.EmployeePostDTO;
import com.prueba.proyectoempleados.exception.EmployeeNotFoundException;
import com.prueba.proyectoempleados.model.Employee;
import com.prueba.proyectoempleados.model.Gender;
import com.prueba.proyectoempleados.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

//    @Autowired
//    private EmployeeRepository employeeRepository;

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


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

    @Override
    public void updateEmployee(Integer idEmployee, EmployeePostDTO employeePostDTO) throws EmployeeNotFoundException {
        //Optional<String> nuevoOptional = Optional.of("hola");
        Optional<Employee> optEmployee = employeeRepository.findById(idEmployee);
        if (optEmployee.isPresent()) {
            //codigo para upoate
            Employee employee = optEmployee.get();
            employee.setFirstName(employeePostDTO.getFirstName());
            employee.setLastName(employeePostDTO.getLastName());
            employee.setGender(employeePostDTO.getGender());
            employee.setHireDate(employeePostDTO.getHireDate());
            employee.setBirthDate(employeePostDTO.getBirthDate());

            employeeRepository.save(employee);

        } else {
            //no hay elemnto
            throw new EmployeeNotFoundException(String.format("Employee not found id  %d", idEmployee));
        }
    }

    @Override
    public void deleteEmployee(Integer idEmployee) throws EmployeeNotFoundException {
        //Optional<String> nuevoOptional = Optional.of("hola");
        Optional<Employee> optEmployee = employeeRepository.findById(idEmployee);
        if (optEmployee.isPresent()) {
            //codigo para upoate
            Employee employee = optEmployee.get();
            employeeRepository.delete(employee);

        } else {
            //no hay elemnto
            throw new EmployeeNotFoundException(String.format("Employee not found id  %d", idEmployee));
        }
    }

    @Override
    public List<EmployeeDTO> getEmployeesByGender(Gender gender) {
        List<Employee> employees = employeeRepository.findAllByGender(gender);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    employee.getIdEmployee(),
                    employee.getFirstName(),
                    employee.getLastName());
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    @Override
    public List<EmployeeDTO> getEmployeesByBirthDate(Date startDate, Date endDate, Gender gender) {
        List<Employee> employees = employeeRepository.findAllByBirthDateBetweenAndGenderOrderByBirthDateAsc(startDate,endDate,gender);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    employee.getIdEmployee(),
                    employee.getFirstName(),
                    employee.getLastName());
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    @Override
    public EmployeeDTO getEmployeeBycompleteName(String firstName, String lastName) throws EmployeeNotFoundException {
        Optional<Employee> optEmployee = employeeRepository.findEmployeeBycompleteName(firstName, lastName);
        if (optEmployee.isPresent()) {
            //codigo para upoate
            Employee employee = optEmployee.get();

            return new EmployeeDTO(employee.getIdEmployee(),employee.getFirstName(),employee.getLastName());

        } else {
            //no hay elemnto
            throw new EmployeeNotFoundException(String.format("Employee not found with name  %s %s", firstName, lastName));
        }
    }
}
