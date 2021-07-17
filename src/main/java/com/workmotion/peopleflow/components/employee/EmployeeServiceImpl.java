package com.workmotion.peopleflow.components.employee;

import java.util.Optional;
import javax.transaction.Transactional;
import com.workmotion.peopleflow.common.enums.EmployeeStateEnum;
import com.workmotion.peopleflow.common.error.exceptions.EmployeeAlreadyExistException;
import com.workmotion.peopleflow.common.error.exceptions.EmployeeNotFoundException;
import com.workmotion.peopleflow.common.error.exceptions.InvalidParamException;
import com.workmotion.peopleflow.components.employee.dao.EmployeeEntity;
import com.workmotion.peopleflow.components.employee.dto.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/12/2021 by OLE
 */
@Service
@RequiredArgsConstructor
class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Employee addEmployee(final Employee employee) {
        checkEmployeeExistenceByPhoneNumber(employee.getPhoneNumber());
        return employeeMapper.toEmployee(employeeRepository.save(employeeMapper.toEmployeeEntity(employee)));
    }

    @Override
    @Transactional
    public Employee updateEmployeeState(Long id, String state) {
        if (!EmployeeStateEnum.contains(state)){
            throw new InvalidParamException(state);
        }
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        employeeEntity.setState(state);
        return employeeMapper.toEmployee(employeeEntity);
    }

    void checkEmployeeExistenceByPhoneNumber(String phoneNumber){
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findByPhoneNumber(phoneNumber);
        if (employeeEntity.isPresent()){
            throw new EmployeeAlreadyExistException(phoneNumber);
        }
    }

}
