package com.workmotion.peopleflow.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workmotion.peopleflow.common.error.codes.ApiErrorCode;
import com.workmotion.peopleflow.common.error.exceptions.InvalidParamException;
import com.workmotion.peopleflow.components.employee.EmployeeService;
import com.workmotion.peopleflow.components.employee.dto.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/17/2021 by OLE
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    private final String BASE_URI = "/api/employee";

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenAddEmployeeWithInvalidBodyNullPhoneNumberThenFailAndReturn_400_MISSING_BODY_FIELD() throws Exception {
        // given
        Employee employee = Employee.builder()
                .name("name")
                .age(20)
                .title("Developer")
                .build();
        // when
        //then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(BASE_URI)
                        .content(objectMapper.writeValueAsString(employee))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ApiErrorCode.MISSING_BODY_FIELD.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", is(ApiErrorCode.MISSING_BODY_FIELD.name())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").isNotEmpty());
    }

    @Test
    void whenAddEmployeeWithInvalidBodyBlankNameThenFailAndReturn_400_MISSING_BODY_FIELD() throws Exception {
        // given
        Employee employee = Employee.builder()
                .name("")
                .phoneNumber("0122155454")
                .age(20)
                .title("Developer")
                .build();
        // when
        //then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(BASE_URI)
                        .content(objectMapper.writeValueAsString(employee))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ApiErrorCode.MISSING_BODY_FIELD.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", is(ApiErrorCode.MISSING_BODY_FIELD.name())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").isNotEmpty());
    }

    @Test
    void whenAddEmployeeWithInvalidBodyLessThan18AgeThenFailAndReturn_400_MISSING_BODY_FIELD() throws Exception {
        // given
        Employee employee = Employee.builder()
                .name("name")
                .phoneNumber("0122155454")
                .age(12)
                .title("Developer")
                .build();
        // when
        //then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(BASE_URI)
                        .content(objectMapper.writeValueAsString(employee))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ApiErrorCode.MISSING_BODY_FIELD.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", is(ApiErrorCode.MISSING_BODY_FIELD.name())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").isNotEmpty());
    }

    @Test
    void whenAddEmployeeWithValidBodyThenSuccess() throws Exception {
        // given
        Employee employee = Employee.builder()
                .name("name")
                .phoneNumber("0122155454")
                .age(20)
                .title("Developer")
                .state("ADDED")
                .build();
        // when
        Employee employeeResult = Employee.builder()
                .id(5L)
                .name("name")
                .phoneNumber("0122155454")
                .age(20)
                .title("Developer")
                .state("ADDED")
                .build();

        given(employeeService.addEmployee(any())).willReturn(employeeResult);

        //then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(BASE_URI)
                        .content(objectMapper.writeValueAsString(employee))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(employeeResult.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state", is(employeeResult.getState())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(employeeResult.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber", is(employeeResult.getPhoneNumber())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", is(employeeResult.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").isEmpty());
    }

    @Test
    void whenUpdateEmployeeStateWithInvalidStateThenFailAndReturn_400_INVALID_PARAM_VALUE() throws Exception {
        // given
        String state = "invalid";
        // when
        given(employeeService.updateEmployeeState(10L, state)).willThrow(new InvalidParamException(state));
        //then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .patch(BASE_URI+"/10")
                        .queryParam("state", state)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ApiErrorCode.INVALID_PARAM_VALUE.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", is(ApiErrorCode.INVALID_PARAM_VALUE.name())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").isNotEmpty());
    }
}
