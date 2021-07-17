package com.workmotion.peopleflow.controllers;

import javax.validation.Valid;
import com.workmotion.peopleflow.common.enums.EmployeeStateEnum;
import com.workmotion.peopleflow.components.employee.EmployeeService;
import com.workmotion.peopleflow.components.employee.dto.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/12/2021 by OLE
 */
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
public class EmployeeController implements EmployeeControllerApi {

    private final EmployeeService employeeService;

    @Override
    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@Valid @RequestBody final Employee employee) {
        employee.setState(EmployeeStateEnum.ADDED.getState());
        return employeeService.addEmployee(employee);
    }

    @Override
    @PatchMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployeeState(@PathVariable final Long id, @RequestParam final String state) {
        return employeeService.updateEmployeeState(id, state.strip().toUpperCase());
    }

}
