package com.workmotion.peopleflow.common.error.exceptions;

import com.workmotion.peopleflow.common.error.codes.ApiErrorCode;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/12/2021 by OLE
 */
public class EmployeeNotFoundException extends ServiceException {

    @Override
    public ApiErrorCode getApiErrorCode() {
        return ApiErrorCode.EMPLOYEE_NOT_FOUND;
    }

}
