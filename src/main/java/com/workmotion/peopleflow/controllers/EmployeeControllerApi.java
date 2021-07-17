package com.workmotion.peopleflow.controllers;

import javax.validation.Valid;
import com.workmotion.peopleflow.common.enums.EmployeeStateEnum;
import com.workmotion.peopleflow.common.error.ApiError;
import com.workmotion.peopleflow.components.employee.dto.Employee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/12/2021 by OLE
 */

@Tag(name = "Employee Controller")
public interface EmployeeControllerApi {

    @Operation(summary = "Add a new employee ")
    @ApiResponse(responseCode = "201", description = "The employee has been added",
                 content = @Content(
                         mediaType = "application/json",
                         schema = @Schema(implementation = Employee.class)
                 ))
    @ApiResponse(responseCode = "400", description = "Some parameters are missing or invalid",
                 content = @Content(
                         mediaType = "application/json",
                         schema = @Schema(implementation = ApiError.class)
                 ))
    @ApiResponse(responseCode = "409", description = "The employee already exist",
                 content = @Content(
                         mediaType = "application/json",
                         schema = @Schema(implementation = ApiError.class)
                 ))
    Employee addEmployee(
            @Parameter(description = "The employee to add", required = true, schema = @Schema(implementation = Employee.class)) @Valid Employee employee);

    @Operation(summary = "update an employee state")
    @ApiResponse(responseCode = "200", description = "The employee state has been updated",
                 content = @Content(
                         mediaType = "application/json",
                         schema = @Schema(implementation = Employee.class)
                 ))
    @ApiResponse(responseCode = "400", description = "Some parameters are missing or invalid",
                 content = @Content(
                         mediaType = "application/json",
                         schema = @Schema(implementation = ApiError.class)
                 ))
    @ApiResponse(responseCode = "404", description = "The employee not found",
                 content = @Content(
                         mediaType = "application/json",
                         schema = @Schema(implementation = ApiError.class)
                 ))
    Employee updateEmployeeState(
            @Parameter(description = "The employee ID to be updated", required = true) Long id,
            @Parameter(description = "The new employee state from values : " + EmployeeStateEnum.VALUE_LIST, required = true) String state);

}
