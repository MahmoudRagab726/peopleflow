package com.workmotion.peopleflow.components.employee;

import java.util.Optional;
import com.workmotion.peopleflow.common.error.exceptions.EmployeeAlreadyExistException;
import com.workmotion.peopleflow.common.error.exceptions.EmployeeNotFoundException;
import com.workmotion.peopleflow.common.error.exceptions.InvalidParamException;
import com.workmotion.peopleflow.components.employee.dao.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/17/2021 by OLE
 */
@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void whenCheckEmployeeExistenceByPhoneNumberWithNumberAlreadyExistThenThrow_EmployeeAlreadyExistException() {
        // given
        String number = "45554444";
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .id(10L)
                .name("name")
                .state("ACTIVE")
                .phoneNumber(number)
                .title("Dev")
                .build();
        given(employeeRepository.findByPhoneNumber(number))
                .willReturn(Optional.of(employeeEntity));
        // when
        // then
        assertThrows(EmployeeAlreadyExistException.class, () -> employeeService.checkEmployeeExistenceByPhoneNumber(number));
    }

    @Test
    void whenUpdateEmployeeStateWithInvalidStateThenThrow_InvalidParamException() {
        // given
        Long id = 10L;
        String state = "invalid";
        // when
        // then
        assertThrows(InvalidParamException.class, () -> employeeService.updateEmployeeState(id, state));
    }

    @Test
    void whenUpdateEmployeeStateWithEmployeeIdThanNotExistThenThrow_EmployeeNotFoundException() {
        // given
        Long id = 10L;
        String state = "ACTIVE";
        given(employeeRepository.findById(id)).willReturn(Optional.empty());
        // when
        // then
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.updateEmployeeState(id, state));
    }

}
