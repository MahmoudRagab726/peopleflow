package com.workmotion.peopleflow.common.error.exceptions;

import com.workmotion.peopleflow.common.error.codes.ApiErrorCode;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/16/2021 by OLE
 */
public class EmployeeAlreadyExistException extends ServiceException {

    public EmployeeAlreadyExistException(final String message) {
        super(message);
    }

    @Override
    public ApiErrorCode getApiErrorCode() {
        return ApiErrorCode.EMPLOYEE_ALREADY_EXIST;
    }

}
