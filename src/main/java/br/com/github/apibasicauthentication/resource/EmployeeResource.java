package br.com.github.apibasicauthentication.resource;

import br.com.github.apibasicauthentication.entity.dto.EmployeeDto;
import br.com.github.apibasicauthentication.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/employee")
public class EmployeeResource {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<EmployeeDto> getEmployees(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.addEmployee(employeeDto));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDto));
    }

    @PostMapping(path = "{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable(name = "id") Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
