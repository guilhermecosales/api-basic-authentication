package br.com.github.apibasicauthentication.service.impl;

import br.com.github.apibasicauthentication.entity.Employee;
import br.com.github.apibasicauthentication.entity.dto.EmployeeDto;
import br.com.github.apibasicauthentication.repository.EmployeeRepository;
import br.com.github.apibasicauthentication.service.EmployeeService;
import br.com.github.apibasicauthentication.service.exception.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        fromDtoToEntity(employeeDto, employee);
        employee = employeeRepository.save(employee);
        return new EmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().map(EmployeeDto::new).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return new EmployeeDto(optionalEmployee.orElseThrow(() -> new NotFoundException("Employee not found")));
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.getReferenceById(id);
        fromDtoToEntity(employeeDto, employee);
        employee = employeeRepository.save(employee);
        return new EmployeeDto(employee);
    }

    @Override
    public void deleteById(Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Employee not found");
        }
    }

    private void fromDtoToEntity(EmployeeDto employeeDto, Employee employee) {
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
    }

}
