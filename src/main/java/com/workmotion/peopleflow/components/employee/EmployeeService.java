package com.workmotion.peopleflow.components.employee;

import com.workmotion.peopleflow.components.employee.dto.Employee;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/12/2021 by OLE
 */
public interface EmployeeService {

    Employee addEmployee(Employee employee);

    Employee updateEmployeeState(Long id, String state);
}
