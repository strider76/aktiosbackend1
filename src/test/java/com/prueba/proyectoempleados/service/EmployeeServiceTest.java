package com.prueba.proyectoempleados.service;

import com.prueba.proyectoempleados.dao.EmployeeRepository;
import com.prueba.proyectoempleados.dto.EmployeeDTO;
import com.prueba.proyectoempleados.exception.EmployeeNotFoundException;
import com.prueba.proyectoempleados.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @MockBean
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setup() {
        Employee employeeMock = new Employee();
        employeeMock.setFirstName("manolo");
        employeeMock.setLastName("de los palotes");

        Optional<Employee> optEmployee = Optional.of(employeeMock);
        Mockito.when(employeeRepository.findEmployeeBycompleteName("manolo","de los palotes")).thenReturn(optEmployee);
    }

    @Test
    void getEmployeeByNameTest() throws EmployeeNotFoundException {
        EmployeeDTO employee = employeeService.getEmployeeBycompleteName("manolo","de los palotes");
        EmployeeDTO employeeEsperado = new EmployeeDTO();
        employeeEsperado.setName("manolo");
        employeeEsperado.setLastName("de los palotes");
        Assertions.assertThat(employee).isEqualToComparingOnlyGivenFields(employeeEsperado,"name","lastName");

    }

    @Test
    void getEmployeeNotFouindExceptiponTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.getEmployeeBycompleteName("",""));
    }

}
