package com.workmotion.peopleflow.common.error.codes;

import com.workmotion.peopleflow.common.error.ApiError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/16/2021 by OLE
 */
public enum ApiErrorCode implements IApiErrorCode {

    INTERNAL_SERVICE_ERROR(1, HttpStatus.INTERNAL_SERVER_ERROR, "There is something went wrong"),

    EMPLOYEE_NOT_FOUND(2, HttpStatus.NOT_FOUND, "Employee not found"),

    EMPLOYEE_ALREADY_EXIST(3, HttpStatus.CONFLICT, "Employee already exist"),

    MISSING_QUERY_PARAM(4, HttpStatus.BAD_REQUEST, "Missing query-string parameter"),

    MISSING_BODY_FIELD(5, HttpStatus.BAD_REQUEST, "Invalid body field"),

    INVALID_PARAM_VALUE(6, HttpStatus.BAD_REQUEST, "Invalid parameter value");

    @Getter
    private final int code;

    @Getter
    private final HttpStatus httpStatus;

    @Getter
    private final String message;

    ApiErrorCode(int code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public ApiError toResponseEntity() {
        return new ApiError(code, name(), message);
    }

    @Override
    public ApiError toResponseEntity(String detailedMessage) {
        return new ApiError(code, name(), message + ": " + detailedMessage);
    }
}
