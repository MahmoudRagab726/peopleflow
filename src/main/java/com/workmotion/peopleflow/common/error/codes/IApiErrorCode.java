package com.workmotion.peopleflow.common.error.codes;

import com.workmotion.peopleflow.common.error.ApiError;
import org.springframework.http.HttpStatus;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/16/2021 by OLE
 */
public interface IApiErrorCode {

    HttpStatus getHttpStatus();

    ApiError toResponseEntity();

    ApiError toResponseEntity(String detailedMessage);

}
