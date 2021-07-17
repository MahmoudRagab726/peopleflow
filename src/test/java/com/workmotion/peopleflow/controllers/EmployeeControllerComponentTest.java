package com.workmotion.peopleflow.controllers;

import com.workmotion.peopleflow.common.enums.EmployeeStateEnum;
import com.workmotion.peopleflow.components.employee.dto.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/17/2021 by OLE
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.liquibase.change-log=classpath:/liquibase/master-test.xml")
class EmployeeControllerComponentTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URI = "/api/employee";

    @Test
    void whenAddEmployeeWithValidRequestThenSuccessWith_Status_201() {
        // given
        Employee employee = Employee.builder()
                .name("name")
                .phoneNumber("0122155450")
                .age(20)
                .title("Developer")
                .build();
        ResponseEntity<Employee> response = restTemplate.postForEntity(BASE_URI ,employee, Employee.class);
        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(employee.getName());
        assertThat(response.getBody().getState()).isEqualTo(EmployeeStateEnum.ADDED.getState());
        assertThat(response.getBody().getTitle()).isEqualTo(employee.getTitle());
        assertThat(response.getBody().getAddress()).isNull();
        assertThat(response.getBody().getPhoneNumber()).isEqualTo(employee.getPhoneNumber());
    }

    @Test
    @Sql("classpath:/liquibase/data/add-employee-data.sql")
    void whenUpdateEmployeeWithValidRequestThenSuccessWith_Status_200() {
        // given
        Employee employee = Employee.builder()
                .id(10L)
                .name("name")
                .phoneNumber("125441122")
                .age(27)
                .state(EmployeeStateEnum.ACTIVE.getState())
                .title("Developer")
                .address("address")
                .build();
        // when
        ResponseEntity<Employee> response = restTemplate.exchange(BASE_URI + "/10?state=ACTIVE" , HttpMethod.PATCH, null, Employee.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(employee.getId());
        assertThat(response.getBody().getName()).isEqualTo(employee.getName());
        assertThat(response.getBody().getState()).isEqualTo(employee.getState());
        assertThat(response.getBody().getTitle()).isEqualTo(employee.getTitle());
        assertThat(response.getBody().getAddress()).isEqualTo(employee.getAddress());
        assertThat(response.getBody().getPhoneNumber()).isEqualTo(employee.getPhoneNumber());
    }
}
