package com.workmotion.peopleflow.common.error.exceptions;

import com.workmotion.peopleflow.common.error.codes.ApiErrorCode;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/17/2021 by OLE
 */
public class InvalidParamException extends ServiceException {

    public InvalidParamException(final String message) {
        super(message);
    }

    @Override
    public ApiErrorCode getApiErrorCode() {
        return ApiErrorCode.INVALID_PARAM_VALUE;
    }

}
