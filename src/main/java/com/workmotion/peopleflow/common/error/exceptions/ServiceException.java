package com.workmotion.peopleflow.common.error.exceptions;

import com.workmotion.peopleflow.common.error.codes.ApiErrorCode;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/12/2021 by OLE
 */
public abstract class ServiceException extends RuntimeException{

    ServiceException(String message) {
        super(message);
    }

    ServiceException() {
        super();
    }

    public abstract ApiErrorCode getApiErrorCode();

}
