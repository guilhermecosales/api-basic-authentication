package br.com.github.apibasicauthentication.service;

import br.com.github.apibasicauthentication.entity.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto addEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto findById(Long id);

    EmployeeDto updateEmployee(EmployeeDto employeeDto);

    void deleteById(Long id);

}
